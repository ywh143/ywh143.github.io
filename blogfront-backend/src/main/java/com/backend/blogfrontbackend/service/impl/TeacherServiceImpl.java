package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.teacher.Teacher;
import com.backend.blogfrontbackend.mapper.TeacherMapper;
import com.backend.blogfrontbackend.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Teacher findTeacherById(int id) {
        return teacherMapper.findTeacherById(id);
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherMapper.findAllTeacher();
    }

    @Override
    public List<Teacher> findTeacherByName(String name) {
        return teacherMapper.findTeacherByName(name);
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public boolean deleteTeacher(int id) {
        return teacherMapper.deleteTeacher(id);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }
}
