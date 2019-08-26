package com.blog.blogBack.controller;

import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.util.Tools;
import com.blog.blogBack.util.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.security.util.KeyUtil;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UploadController {
    @Resource
    private UploadUtil uploadUtil;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String fileUrl = uploadUtil.uploadFile(inputStream, Tools.getUUID() + Tools.getUUID()+ Tools.getUUID());
            Map<String, String> resultMap = new HashMap<>();
            if (fileUrl == null)
                return ResultGenerator.genFailResult("服务器繁忙，请稍后重试！");
            else {
                resultMap.put("url", fileUrl);
                return ResultGenerator.genSuccessResult(resultMap);
            }
        } else {
            return ResultGenerator.genFailResult("文件不能为空！");
        }
    }
}
