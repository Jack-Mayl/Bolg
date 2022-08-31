package com.longzai.service.impl;

import com.google.gson.Gson;
import com.longzai.domain.ResponseResult;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import com.longzai.service.UploadService;
import com.longzai.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RGBImageFilter;
import java.io.FileInputStream;
import java.io.InputStream;

@Service("UploadServiceImpl")
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String key;
    private String url;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //TODO 判断文件类型或者文件大小
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){

            throw new SystemException(AppHttpCodeEnum.FILE_TYPR_ERROR);
        }

        String filePath = PathUtils.generateFilePath(originalFilename);
        //如果判断通过上传文件到Oss
        String url = uploadOss(img,filePath);
        return ResponseResult.okResult(url);
    }
    private String  uploadOss(MultipartFile imgFile,String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            InputStream inputStream=imgFile.getInputStream();
            String upToken = auth .uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, filePath, upToken,null,null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
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
            ex.printStackTrace();
        }
        return "www";
    }
}
