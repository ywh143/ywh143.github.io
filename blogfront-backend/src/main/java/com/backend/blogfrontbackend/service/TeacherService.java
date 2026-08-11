package com.backend.blogfrontbackend.service;


import com.backend.blogfrontbackend.entity.teacher.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherById(int id);

    List<Teacher> findAllTeacher();

    List<Teacher> findTeacherByName(String name);

    boolean insertTeacher(Teacher teacher);

    boolean deleteTeacher(int id);

    boolean updateTeacher(Teacher teacher);
}
