package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-20 21:14
 */
@RestController
@Api(tags = "文件上传")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "图片上传接口")
    @ApiImplicitParam(name = "img", value = "png或jpg文件", required = true)
    public ResponseResult<String> uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}