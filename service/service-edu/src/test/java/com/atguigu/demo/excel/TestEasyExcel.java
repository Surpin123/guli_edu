package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
  public static void main(String[] args) {
/*    // 1 设置写入的文件夹地址和Excel名称

    String filename = "/Users/surbinchang/Downloads/write.xlsx";

    // 2 调用写方法
    // write() 两个参数：文件名，类名称class
    EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());*/


    // 读操作
    String filename = "/Users/surbinchang/Downloads/write.xlsx";
    EasyExcel.read(filename, DemoData.class,new ExcelListener()).sheet().doRead();


  }

  private static List<DemoData> getData(){
    ArrayList<DemoData> demoDatas = new ArrayList<>();
    for(int i = 0;i<10;i++){
      DemoData data = new DemoData();
      data.setSno(i);
      data.setSname("lucy" + i);
      demoDatas.add(data);
    }
    return demoDatas;
  }
}
