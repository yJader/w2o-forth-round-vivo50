package com.yj.service;

import com.yj.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Package com.yj.service
 * @Author yJade
 * @Date 2023-02-20 21:15
 */
public interface UploadService {
    public ResponseResult<String> uploadImg(MultipartFile img) ;
}
