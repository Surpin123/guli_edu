package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.service.EduGetMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/eduservice/subject/hashmap")
public class EduGetMapController {

  @Autowired
  private EduGetMapService eduGetMapService;

  @GetMapping("getAll")
  public R getAll(){
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("level",2);

    List<HashMap<String,Object>> resultMap =  eduGetMapService.getAll(paramMap);

    return R.ok().data("resultMap",resultMap);
  }
}
