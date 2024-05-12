package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

  // 监听器不能交给Spring管理，需要自己管理

  private EduSubjectService eduSubjectService;
  public SubjectExcelListener(){};

  public SubjectExcelListener(EduSubjectService eduSubjectService) {
    this.eduSubjectService = eduSubjectService;
  }

  @Override
  public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
    if(subjectData == null){
        throw new NullPointerException();
    }

    // 一行行的读取
    // 判断一级分类陈总重复
    EduSubject existsOneSubject = this.existsOneSubject(eduSubjectService, subjectData.getOneSubjectName());
    if (existsOneSubject == null){
      existsOneSubject = new EduSubject();
      existsOneSubject.setParentId("0");
      existsOneSubject.setTitle(subjectData.getOneSubjectName());
      eduSubjectService.save(existsOneSubject);
    }

    String pid = existsOneSubject.getId();
    EduSubject existsTwoSubject = this.existsTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
    if (existsTwoSubject == null){
      existsTwoSubject = new EduSubject();
      existsTwoSubject.setParentId(pid);
      existsTwoSubject.setTitle(subjectData.getTwoSubjectName());

      eduSubjectService.save(existsTwoSubject);
    }



  }

  private EduSubject existsOneSubject(EduSubjectService eduSubjectService,String name){
    LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle,name).eq(EduSubject::getParentId,"0");
    EduSubject one = eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
    return one;
  }

  private EduSubject existsTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
    LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle,name).eq(EduSubject::getParentId,pid);
    EduSubject two = eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
    return two;
  }


  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
