package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.course.CourseResource;
import com.backend.blogfrontbackend.mapper.CourseResourceMapper;
import com.backend.blogfrontbackend.service.CourseResourceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceServiceImpl implements CourseResourceService {

    @Resource
    CourseResourceMapper mapper;

    @Override
    public CourseResource findCourseResourceByChapterId(int id) {
        return mapper.findCourseResourceByChapterId(id);
    }
}
