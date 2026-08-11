package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.CourseResource;

import java.util.List;

public interface CourseResourceService {

    CourseResource findCourseResourceByChapterId(int id);

}
