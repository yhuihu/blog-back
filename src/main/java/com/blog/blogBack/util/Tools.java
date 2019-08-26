package com.blog.blogBack.util;

import com.alibaba.fastjson.JSON;
import com.blog.blogBack.config.WebConfig;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-08-01
 **/
public class Tools {
    private final Logger logger = LoggerFactory.getLogger(Tools.class);
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public static Integer getRandomCode(Integer min, Integer max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Result checkFormError(BindingResult bindingResult) {
        Result result = new Result();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            result.setCode(ResultCode.FAIL);
            result.setMessage(errors.get(0).getDefaultMessage());
            return result;
        } else {
            return null;
        }
    }

    public static String getUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
    /*
     * 申请一个大小在1000到100000之间的随机数
     * */
//    public static void main(String[] args){
//        System.out.println(getRandomCode(1000,100000));
//    }

    public void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
