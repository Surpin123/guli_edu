package com.atguigu.commonutils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class BaseDB {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSession session;

    public Object selectOne(String statement, Map<String,Object> paraMap){
        Object o = session.selectOne(statement, paraMap);
        return o;
    }

    public List<Object> selectList(String statement, Map<String,Object> paraMap){
        List<Object> objects = session.selectList(statement, paraMap);
        return objects;
    }

}
