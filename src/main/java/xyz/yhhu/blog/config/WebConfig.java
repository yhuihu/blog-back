package xyz.yhhu.blog.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultCode;
import xyz.yhhu.blog.framework.ServiceException;
import xyz.yhhu.blog.utils.IpAddressUtil;
import xyz.yhhu.blog.utils.Tools;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.config
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Resource
    private RedisTemplate redisTemplate;

    private final Tools tools = new Tools();

    /**
     * 消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        // 按需配置，更多参考FastJson文档哈
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(converter);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        //编码
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 统一异常处理
     *
     * @return void
     **/
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e) -> {
            Result result = new Result();
            if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                logger.info(e.getMessage());
            } else if (e instanceof NoHandlerFoundException) {
                result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
            } else if (e instanceof ServletException) {
                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
            } else {
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
                } else {
                    message = e.getMessage();
                }
                logger.error(message, e);
            }
            tools.responseResult(response, result);
            return new ModelAndView();
        });
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                // 过滤预请求
                if (!request.getMethod().equals("OPTIONS")) {
                    Result result = new Result();
                    String token = request.getHeader("X-Token");
                    result.setCode(ResultCode.TOKEN_ERROR).setMessage("请先登录");
                    if (token != null) {
                        List<String> list = redisTemplate.opsForList().range(token, 0, -1);
                        String ipAddr = IpAddressUtil.getIpAddress(request);
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        if (!list.isEmpty()) {
                            for (String target : list) {
                                if (target.equals(ipAddr)) {
                                    return true;
                                }
                            }
                        } else {
                            result.setCode(ResultCode.TOKEN_ERROR).setMessage("Token过期，请重新登录");
                        }
                    }
                    tools.responseResult(response, result);
                    logger.info("重定向到登录界面");
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/admin/**").excludePathPatterns("/admin");
    }
}
