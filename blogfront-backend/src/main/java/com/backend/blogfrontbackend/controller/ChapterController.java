package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.Course;
import com.backend.blogfrontbackend.service.ChapterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Resource
    ChapterService chapterService;

    @GetMapping("/Chapter")
    RestBean<List<Chapter>> findAllCourse() {
        List<Chapter> list = chapterService.findAllChapter();
        return RestBean.success(list);
    }

    @GetMapping("/Chapter/{id}")
    RestBean<List<Chapter>> findChapterByCourseId(@PathVariable("id") String id) {
        List<Chapter> list = chapterService.findChapterByCourseId(Integer.parseInt(id));
        return RestBean.success(list);
    }

}
