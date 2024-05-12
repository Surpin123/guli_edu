package com.atguigu.eduservice.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EduGetMapService {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  public List<HashMap<String, Object>> getAll(HashMap<String,Object> paramMap) {

    HashMap<String, Object> resultMap = new HashMap<>();
    List<HashMap<String,Object>> objects = sqlSessionTemplate.selectList("EduGetMap.getAll", paramMap);

    return objects;
  }
}
