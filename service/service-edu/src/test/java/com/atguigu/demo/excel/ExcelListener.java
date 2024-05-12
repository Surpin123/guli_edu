package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {


  // 一行一行的读取
  @Override
  public void invoke(DemoData demoData, AnalysisContext analysisContext) {
    System.out.println("*****" + demoData);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    System.out.println("表头：" + headMap);
  }

  // 取出之后的处理
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
