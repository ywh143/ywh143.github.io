package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.CourseResource;
import com.backend.blogfrontbackend.service.ChapterService;
import com.backend.blogfrontbackend.service.CourseResourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courseResource")
public class CourseResourceController {

    @Resource
    CourseResourceService courseResourceService;

    @GetMapping("/CourseResource/{id}")
    RestBean<CourseResource> findCourseResourceByChapterId(@PathVariable("id") String id) {
        CourseResource courseResource = courseResourceService.findCourseResourceByChapterId(Integer.parseInt(id));
        return RestBean.success(courseResource);
    }

}
