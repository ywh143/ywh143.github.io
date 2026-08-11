package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.CourseResource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseResourceMapper {

    @Select("select * from blog.course_resource where chapter_id = #{id}")
    @Results({
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "resourceType", column = "resource_type"),
            @Result(property = "resourceUrl", column = "resource_url"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    CourseResource findCourseResourceByChapterId(int id);

}
