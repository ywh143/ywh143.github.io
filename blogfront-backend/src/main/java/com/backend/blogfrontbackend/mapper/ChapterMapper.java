package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.course.Chapter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChapterMapper {

    @Select("select * from blog.chapter")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "course", column = "course_id", many =
            @Many(select = "com.backend.blogfrontbackend.mapper.CourseMapper.findCourseById")),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    List<Chapter> findAllChapter();

    @Select("select * from blog.chapter where course_id = #{id}")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "course", column = "course_id", many =
            @Many(select = "com.backend.blogfrontbackend.mapper.CourseMapper.findCourseById")),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    List<Chapter> findChapterByCourseId(int id);


}
