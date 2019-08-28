package com.blog.blogBack.controller.admin;

import com.blog.blogBack.entity.User;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.UserService;
import com.blog.blogBack.util.EmailTool;
import com.blog.blogBack.util.IpAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by CodeGenerator on 2019/07/07.
 * <p>
 * 管理员是唯一的，所以没有提供add、delete之类的操作。
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminAdminController {
    @Resource
    private UserService adminService;
    @Resource
    EmailTool emailTool;
    @Value("${email.adminEmail}")
    private String adminEmail;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/update")
    public Result update(User admin) {
        admin.setId(1);
        adminService.update(admin);
        redisTemplate.delete("adminUser");
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail() {
        User admin =
                redisTemplate.opsForValue().get("adminUser") == null ?
                        adminService.findById(1) : (User) redisTemplate.opsForValue().get("adminUser");
        return ResultGenerator.genSuccessResult(admin);
    }

    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest request) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String ipAddr = IpAddressUtil.getIpAdrress(request);
        //密码错误次数
        Integer count = (Integer) valueOperations.get(ipAddr);
        if (count != null && count == 5) {
            long time = redisTemplate.getExpire(ipAddr);
            return ResultGenerator.genFailResult("密码错误次数过多，请" + time + "秒后重试");
        }
        User admin = adminService.Login(username, password);
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
                    emailTool.sendSimpleMail(adminEmail, "异常登录提醒", "异常登录ip地址为:" + IpAddressUtil.getIpAdrress(request));
                }
                return ResultGenerator.genFailResult("用户名或密码错误！当前密码错误次数:" + count + "次");
            }
        }
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        redisTemplate.opsForList().remove(request.getHeader("X-Token"), 0, IpAddressUtil.getIpAdrress(request));
        return ResultGenerator.genSuccessResult("注销成功！");
    }
}
