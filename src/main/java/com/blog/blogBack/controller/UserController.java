package com.blog.blogBack.controller;

import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.entity.User;
import com.blog.blogBack.entity.dto.LoginDto;
import com.blog.blogBack.entity.dto.RegisterDto;
import com.blog.blogBack.entity.vo.AdminVO;
import com.blog.blogBack.entity.vo.UserVO;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultCode;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.ReaderService;
import com.blog.blogBack.service.UserService;
import com.blog.blogBack.util.EmailTool;
import com.blog.blogBack.util.Encryption;
import com.blog.blogBack.util.IpAddressUtil;
import com.blog.blogBack.util.Tools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: blog-back
 * @description:
 * @author: Tiger
 * @create: 2019-07-28 22:04
 **/
@RestController
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private EmailTool emailTool;

    @RequestMapping("login")
    public Result login(@Valid LoginDto loginDto, BindingResult bindingResult) {
        if (Tools.checkFormError(bindingResult) != null) {
            return Tools.checkFormError(bindingResult);
        }
        //密码错误次数
        Integer count = 0;
        Map<String, Object> map = new HashMap<>();
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        String msg = "出现未知错误";
        Reader reader = new Reader();
        Result<Map> result = new Result<>();
        UserVO userVo = new UserVO();
        reader = readerService.Login(username, password);
        //账号或密码错误
        if (reader == null) {
            if (redisTemplate.opsForValue().get(username) == null) {
                redisTemplate.opsForValue().set(username, 1);
                msg = "密码错误，当前密码错误次数:1次";
            } else if ((Integer) redisTemplate.opsForValue().get(username) == 5) {
                //密码错误次数过多，获取剩余多少时间解封
                long time = redisTemplate.getExpire(username);
                msg = "密码错误次数过多，请" + time + "秒后重试";
            } else {
                count = (Integer) redisTemplate.opsForValue().get(username);
                redisTemplate.opsForValue().set(username, ++count);
                if (count == 5) {
                    //达到5次密码错误，封锁账号
                    redisTemplate.expire(username, 1, TimeUnit.HOURS);
                }
                msg = "密码错误，当前密码错误次数:" + count + "次";
            }
        } else {
            if (redisTemplate.opsForValue().get(username) != null) {
                redisTemplate.delete(username);
            }
            msg = "登录成功";
            //保存token到redis
            map.put("Token", reader.getPassword());
            redisTemplate.opsForValue().set("Token:" + reader.getPassword(), reader);
            redisTemplate.expire("Token:" + reader.getPassword(), 30, TimeUnit.DAYS);
        }
        result.setMessage(msg);
        if (reader != null) {
            userVo = userVo.transToUserVo(reader);
        } else {
            userVo = null;
        }
        map.put("User", userVo);
        result.setData(map);
        return result;
    }

    @RequestMapping("getRegisterCode")
    public Result getRegisterCode(String email) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL);
        if (readerService.hasReaderEmail(email) == null) {
            if (redisTemplate.opsForValue().get("Register:" + email) == null) {
                Integer rand = Tools.getRandomCode(10, 100000);// 随机数作为验证注册用
                try{
                    emailTool.sendSimpleMail(email, "来自Blog的注册验证码", "您的验证码为：" + rand);
                    redisTemplate.opsForValue().set("Register:" + email, rand);
                    redisTemplate.expire("Register:" + email, 5, TimeUnit.MINUTES);
                    result.setCode(ResultCode.SUCCESS);
                    result.setMessage("获取验证码成功，请在5分钟内完成操作！");
                }catch (MailException m){
                    result.setMessage("出现未知错误，请确保邮箱无误！");
                }
            } else {
                result.setMessage("请勿重复获取验证码！");
            }
        } else {
            result.setMessage("该邮箱已被注册！");
        }
        return result;
    }

    @RequestMapping("doRegister")
    public Result doRegister(@Valid RegisterDto registerDto, BindingResult bindingResult, HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL);    //错误码400，没有获取验证码或用户名被注册
        if (Tools.checkFormError(bindingResult) != null) {
            return Tools.checkFormError(bindingResult);
        }
        if(redisTemplate.opsForValue().get("Register:" + registerDto.getEmail())==null){
            result.setMessage("请先获取验证码！");
        }else{
            if(readerService.hasReaderName(registerDto.getUsername())!=null){
                result.setMessage("该账户名已被注册！");
            }else {
                result.setCode(ResultCode.SUCCESS);
                Reader reader=new Reader();
                reader.setUsername(registerDto.getUsername());
                reader.setName(registerDto.getUsername());
                reader.setPassword(Encryption.generate(reader.getUsername(),reader.getPassword()));
                reader.setEmail(registerDto.getEmail());
                reader.setIp(IpAddressUtil.getIpAdrress(request));
                readerService.save(reader);
            }
        }
        return result;
    }

    @GetMapping("/detail")
    public Result detail() {
        User admin=new User();
        if(redisTemplate.opsForValue().get("adminUser")==null) {
            admin = userService.findById(1);
            redisTemplate.opsForValue().set("adminUser",admin);
        }else {
            admin= (User) redisTemplate.opsForValue().get("adminUser");
        }
        AdminVO result = new AdminVO();
        BeanUtils.copyProperties(admin, result);
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 登录状态验证
     */
    @RequestMapping("checkToken")
    public Result checkToken(@RequestHeader("Authorization") String Token) {
        Result result = new Result();
        User user = new User();
        System.out.println(Token);
        UserVO userVo = new UserVO();
        if (redisTemplate.opsForValue().get("Token:" + Token) == null) {
            //401
            result.setCode(ResultCode.UNAUTHORIZED);
        } else {
            result.setCode(ResultCode.SUCCESS);
            result.setData(userVo.transToUserVo(
                    (Reader) redisTemplate.opsForValue().get("Token:" + Token))
            );
        }
        return result;
    }

}