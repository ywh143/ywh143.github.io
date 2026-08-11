package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.teacher.Teach;
import com.backend.blogfrontbackend.service.TeachService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teach")
public class TeachController {

    @Resource
    TeachService teachService;

    @GetMapping("/getTeachByTeacherId/{id}")
    RestBean<List<Teach>> findTeachByTeacherId(@PathVariable("id") int id){
        List<Teach> list = teachService.findTeachByTeacherId(id);
        return RestBean.success(list);
    }
    @GetMapping("/getTeachByCourseId/{id}")
    RestBean<List<Teach>> findTeachByCourseId(@PathVariable("id") int id){
        List<Teach> list = teachService.findTeachByCourseId(id);
        return RestBean.success(list);
    }

    @GetMapping("/Teaches")
    RestBean<List<Teach>> findAllTeach(){
        return RestBean.success(teachService.findAllTeach());
    }

    RestBean<String> insertTeach(Teach teach){
        return null;
    }

    RestBean<String> deleteteach(int tid, int cid){
        return null;
    }

    RestBean<String> updateTeach(Teach teach){
        return null;
    }
}
