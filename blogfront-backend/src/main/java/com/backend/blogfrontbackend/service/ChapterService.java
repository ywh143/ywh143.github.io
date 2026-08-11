package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.Course;

import java.util.List;

public interface ChapterService {

    List<Chapter> findAllChapter();

    List<Chapter> findChapterByCourseId(int id);

}
