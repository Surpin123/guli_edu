package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author SurBine
 * @since 2024-04-27
 */

@CrossOrigin
@Api(description = "所有讲师列表")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

  @Autowired
  private EduTeacherService teacherService;

  // 1 查询讲师所有数据
  @ApiOperation(value = "所有讲师列表")
  @GetMapping("findAll")
  public R findAllTeacher() {
    List<EduTeacher> list = teacherService.list(null);
    return R.ok().data("items", list);
  }

  // 2 逻辑删除方法
  @ApiOperation(value = "逻辑删除讲师")
  @DeleteMapping("remove/{id}")
  public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
    boolean flag = teacherService.removeById(id);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }

  }

  // 3 分页查询
  @ApiOperation(value = "分页查询")
  @GetMapping("pageTeacher/{current}/{limit}")
  public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {

    // 创建Page对象
    IPage<EduTeacher> pageTeacher = teacherService.page(new Page<>(current, limit), null);
    List<EduTeacher> records = pageTeacher.getRecords();
    long total = pageTeacher.getTotal();

    return R.ok().data("records", records).data("total", total);
  }

  // 4 条件查询带分页
  @PostMapping("pageTeacherCondition/{current}/{limit}")
  public R pageTeacherCondition(@PathVariable long current,
                                @PathVariable long limit,
                                @RequestBody(required = false) TeacherQuery teacherQuery) {

    LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
    String name = teacherQuery.getName();
    Integer level = teacherQuery.getLevel();
    String begin = teacherQuery.getBegin();
    String end = teacherQuery.getEnd();
    if (!StringUtils.isEmpty(name)) {
      queryWrapper.like(EduTeacher::getName, name);
    }
    if (!StringUtils.isEmpty(level)) {
      queryWrapper.eq(EduTeacher::getLevel, level);
    }
    if (!StringUtils.isEmpty(begin)) {
      queryWrapper.gt(EduTeacher::getGmtCreate, begin);
    }
    if (!StringUtils.isEmpty(end)) {
      queryWrapper.lt(EduTeacher::getGmtCreate, end);
    }
    queryWrapper.orderByDesc(EduTeacher::getGmtCreate);
    IPage<EduTeacher> pageTeacher = teacherService.page(new Page<EduTeacher>(current, limit), queryWrapper);
    List<EduTeacher> records = pageTeacher.getRecords();
    long total = pageTeacher.getTotal();

    return R.ok().data("rows", records).data("total", total);
  }


  // 添加讲师
  @ApiOperation("添加讲师")
  @PostMapping("addTeacher")
  public R addTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean flag = teacherService.save(eduTeacher);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }

  }

  @ApiOperation("根据ID获取讲师")
  @GetMapping("getTeacher/{id}")
  public R getTeacher(@PathVariable String id) {
    EduTeacher eduTeacher = teacherService.getById(id);
    return R.ok().data("teacher", eduTeacher);
  }

  @ApiOperation("修改讲师")
  @PostMapping("updateTeacher")
  public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean flag = teacherService.updateById(eduTeacher);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }

  }

}

