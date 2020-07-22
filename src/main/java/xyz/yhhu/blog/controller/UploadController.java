package xyz.yhhu.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.UploadService;
import xyz.yhhu.blog.utils.Tools;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tiger
 * @date 2020-07-22
 * @see xyz.yhhu.blog.controller
 **/
@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String fileUrl = uploadService.uploadFile(inputStream, Tools.getUUID() + Tools.getUUID()+ Tools.getUUID());
            Map<String, String> resultMap = new HashMap<>(1);
            if (fileUrl == null) {
                return ResultGenerator.genFailResult("服务器繁忙，请稍后重试！");
            } else {
                resultMap.put("url", fileUrl);
                return ResultGenerator.genSuccessResult(resultMap);
            }
        } else {
            return ResultGenerator.genFailResult("文件不能为空！");
        }
    }
}
