package com.yj.service.impl;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yj.domain.ResponseResult;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.UploadService;
import com.yj.utils.PathUtils;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Description:
 * @Package com.yj.service.impl
 * @Author yJade
 * @Date 2023-02-20 21:15
 */
@Service
@PropertySource("classpath:mykey.properties")
@ConfigurationProperties(prefix = "oss")
@Setter
public class OssUploadService implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String url;

    @Override
    public ResponseResult<String> uploadImg(MultipartFile img) {
        // TODO 判断文件类型与大小
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对文件名进行判断
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //判断通过 上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img,filePath);

        return ResponseResult.okResult(url);
    }

    private String uploadOss(MultipartFile imgFile, String filePath) {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream,filePath,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(url+putRet.key);
                System.out.println(putRet.hash);
                return url+putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return null;
    }
}
