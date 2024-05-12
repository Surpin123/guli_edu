package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
@Api("OSS")
public class OssController {

  @Autowired
  private OssService ossService;

  @PostMapping
  public R unloadOssFile(MultipartFile file) {

    String url = ossService.uploadFileAvatar(file);


    return R.ok().data("url",url);

  }
}
