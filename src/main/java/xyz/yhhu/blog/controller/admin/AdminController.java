package xyz.yhhu.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.User;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.UserService;
import xyz.yhhu.blog.utils.EmailTool;
import xyz.yhhu.blog.utils.IpAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Tiger
 * @date 2020-07-20
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    EmailTool emailTool;
    @Value("${email.adminEmail}")
    private String adminEmail;
    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("")
    public Result update(User admin) {
        admin.setId(1);
        userService.updateById(admin);
        redisTemplate.delete("adminUser");
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("")
    public Result login(String username, String password, HttpServletRequest request) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String ipAddr = IpAddressUtil.getIpAddress(request);
        //密码错误次数
        Integer count = (Integer) valueOperations.get(ipAddr);
        if (count != null && count == 5) {
            long time = redisTemplate.getExpire(ipAddr);
            return ResultGenerator.genFailResult("密码错误次数过多，请" + time + "秒后重试");
        }
        User admin = userService.login(username, password);
        if (admin != null) {
            if (valueOperations.get(ipAddr) != null) {
                redisTemplate.delete(ipAddr);
            }
            Map<String, String> tokenMap = new HashMap<>();
            String token = admin.getPassword();
            tokenMap.put("token", token);
            redisTemplate.opsForList().leftPush(token, ipAddr);
            //记录登录日志
            redisTemplate.opsForHash().put(admin.getEmail(), ipAddr, new Date());
            return ResultGenerator.genSuccessResult(tokenMap);
        } else {
            if (count == null) {
                redisTemplate.expire(ipAddr, 1, TimeUnit.DAYS);
                valueOperations.set(ipAddr, 1);
                return ResultGenerator.genFailResult("用户名或密码错误！当前错误次数：1次");
            } else if (count == 5) {
                //密码错误次数过多，获取剩余多少时间解封
                long time = redisTemplate.getExpire(ipAddr);
                return ResultGenerator.genFailResult("密码错误次数过多，请" + time + "秒后重试");
            } else {
                valueOperations.set(ipAddr, ++count);
                if (count == 5) {
                    //达到5次密码错误，封锁ip地址
                    redisTemplate.expire(ipAddr, 1, TimeUnit.DAYS);
                    emailTool.sendSimpleMail(adminEmail, "异常登录提醒", "异常登录ip地址为:" + IpAddressUtil.getIpAddress(request));
                }
                return ResultGenerator.genFailResult("用户名或密码错误！当前密码错误次数:" + count + "次");
            }
        }
    }

    @DeleteMapping("")
    public Result<String> logout(HttpServletRequest request) {
        redisTemplate.opsForList().remove(request.getHeader("X-Token"), 0, IpAddressUtil.getIpAddress(request));
        return ResultGenerator.genSuccessResult("注销成功！");
    }
}
