package com.blog.blogBack.controller;

import com.alibaba.fastjson.JSONObject;
import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultCode;
import com.blog.blogBack.service.ReaderService;
import com.blog.blogBack.util.IpAddressUtil;
import com.blog.blogBack.util.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 第三方登录 Controller
 * </p>
 *
 * @package: com.xkcoding.oauth.controller
 * @description: 第三方登录 Controller
 * @author: yangkai.shen
 * @date: Created in 2019-05-17 10:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OauthController {
    @Value("${justauth.type.github.client-id}")
    private String client_id;
    @Value("${justauth.type.github.client-security}")
    private String client_security;
    @Value("${justauth.type.github.redirect-uri}")
    private String redirect_uri;
    @Value("${blog.homeUrl}")
    private String homeUrl;
    @Autowired
    ReaderService readerService;

    private Tools tools = new Tools();

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
        Reader dataBaseUser = readerService.findBy("uuid", uuid);
        Integer userId = 0;
        if (dataBaseUser == null) {
            dataBaseUser = new Reader();
            String name = (String) object.getJSONObject("data").get("nickname");
            dataBaseUser.setName(name);
            dataBaseUser.setUuid(uuid);
            dataBaseUser.setAvatar((String) object.getJSONObject("data").get("avatar"));
            dataBaseUser.setIp(IpAddressUtil.getIpAdrress(request));
            readerService.save(dataBaseUser);
            Reader newUser = readerService.findBy("uuid", uuid);
            userId = newUser.getId();
        } else {
            dataBaseUser.setName((String) object.getJSONObject("data").get("nickname"));
            dataBaseUser.setAvatar((String) object.getJSONObject("data").get("avatar"));
            userId = dataBaseUser.getId();
        }
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
     * @param source
     * @return
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId(client_id)
                        .clientSecret(client_security)
                        .redirectUri(redirect_uri)
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