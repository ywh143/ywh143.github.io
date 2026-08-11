package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.Course;
import com.backend.blogfrontbackend.mapper.ChapterMapper;
import com.backend.blogfrontbackend.mapper.CourseMapper;
import com.backend.blogfrontbackend.service.ChapterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    ChapterMapper chapterMapper;


    @Override
    public List<Chapter> findAllChapter() {
        return chapterMapper.findAllChapter();
    }

    @Override
    public List<Chapter> findChapterByCourseId(int id) {
        return chapterMapper.findChapterByCourseId(id);
    }
}
