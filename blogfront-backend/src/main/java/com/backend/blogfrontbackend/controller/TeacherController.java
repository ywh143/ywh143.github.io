package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.teacher.Teacher;
import com.backend.blogfrontbackend.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @GetMapping("/Teachers")
    RestBean<List<Teacher>> findAllTeacher(){
        List<Teacher> list = teacherService.findAllTeacher();
        return RestBean.success(list);
    }
}
