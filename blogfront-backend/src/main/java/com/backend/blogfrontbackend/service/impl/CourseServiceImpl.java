package com.backend.blogfrontbackend.service.impl;
import com.backend.blogfrontbackend.entity.course.Course;
import com.backend.blogfrontbackend.mapper.CourseMapper;
import com.backend.blogfrontbackend.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseMapper courseMapper;

    @Override
    public List<Course> findAllCourse() {
        return courseMapper.findAllCourse();
    }

    @Override
    public Course findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public Course findCourseByName(String name) {
        return courseMapper.findCourseByName(name);
    }

    @Override
    public boolean insertCourse(Course course) {
        System.out.println(course+"=============");
        return courseMapper.insertCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseMapper.deleteCourse(id);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public boolean updateCourseStatus(int id, int status) {
        return courseMapper.updateCourseStatus(id,status);
    }
}
