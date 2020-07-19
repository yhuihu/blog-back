package xyz.yhhu.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Reader;
import xyz.yhhu.blog.entity.User;
import xyz.yhhu.blog.entity.vo.AdminVO;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultCode;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.ReaderService;
import xyz.yhhu.blog.service.UserService;
import xyz.yhhu.blog.utils.IpAddressUtil;
import xyz.yhhu.blog.utils.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tiger
 * @date 2020-07-18
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ReaderService readerService;

    @Value("${justauth.type.github.client-id}")
    private String clientId;
    @Value("${justauth.type.github.client-security}")
    private String clientSecurity;
    @Value("${justauth.type.github.redirect-uri}")
    private String redirectUri;
    @Value("${blog.homeUrl}")
    private String homeUrl;
    private Tools tools = new Tools();

    /**
     * 获取作者信息
     *
     * @return Result
     */
    @GetMapping()
    public Result detail() {
        User admin;
        if (redisTemplate.opsForValue().get("adminUser") == null) {
            admin = userService.getById(1);
            redisTemplate.opsForValue().set("adminUser", admin);
        } else {
            admin = (User) redisTemplate.opsForValue().get("adminUser");
        }
        AdminVO result = new AdminVO();
        BeanUtils.copyProperties(admin, result);
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 登录
     *
     * @param source   第三方登录类型
     * @param response response
     * @throws IOException
     */
    @RequestMapping("/login/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        Result result = new Result();
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        Map<String, String> map = new HashMap<>();
        map.put("redirectUrl", authorizeUrl);
        result.setData(map);
        result.setCode(ResultCode.REDIRECT);
        tools.responseResult(response, result);
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public void login(@PathVariable("source") String source, AuthCallback callback, HttpServletResponse response, HttpServletRequest request) throws IOException {
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse targetResponse = authRequest.login(callback);
        JSONObject object = (JSONObject) JSONObject.parse(JSONObject.toJSONString(targetResponse));
        Integer uuid = Integer.valueOf((String) object.getJSONObject("data").get("uuid"));
        QueryWrapper<Reader> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        Reader dataBaseUser = readerService.getOne(queryWrapper);
        Integer userId;
        if (dataBaseUser == null) {
            dataBaseUser = new Reader();
            String name = (String) object.getJSONObject("data").get("nickname");
            dataBaseUser.setName(name);
            dataBaseUser.setUuid(uuid);
            dataBaseUser.setAvatar((String) object.getJSONObject("data").get("avatar"));
            dataBaseUser.setIp(IpAddressUtil.getIpAddress(request));
            readerService.save(dataBaseUser);
        }
        userId = dataBaseUser.getId();
        response.sendRedirect(homeUrl + "?userId=" + userId + "&name=" + dataBaseUser.getName()
                + "&avatar=" + dataBaseUser.getAvatar());
    }

    @RequestMapping("/revoke/{source}/{token}")
    public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) throws IOException {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.revoke(AuthToken.builder().accessToken(token).build());
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source
     * @return
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecurity)
                        .redirectUri(redirectUri)
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }


}
