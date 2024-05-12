package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
  @Override
  public String uploadFileAvatar(MultipartFile file) {
    String endpoint = ConstantPropertiesUtils.END_POINT;
    String accessKeyId = ConstantPropertiesUtils.KEY_ID;
    String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
    String bucketName = ConstantPropertiesUtils.BUCK_ETNAME;

    try {
      OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

      InputStream inputStream = file.getInputStream();

      // 1 让每个文件名称都不相同  UUID
      String uuid = UUID.randomUUID().toString().replaceAll("-", "");

      String fileName = file.getOriginalFilename();
      fileName = uuid + fileName;

      // 2 把文件按照日期分类
      String datePath = new DateTime().toString("yyyy/MM/dd");
      fileName = datePath + "/" + fileName;


      ossClient.putObject(bucketName, fileName, inputStream);

      ossClient.shutdown();

      String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
      return url;

    } catch (IOException e) {

      throw new RuntimeException(e);
    }
  }
}
