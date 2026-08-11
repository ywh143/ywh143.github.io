package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.course.Chapter;
import com.backend.blogfrontbackend.entity.course.LearningProgress;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LearningProgressMapper {

    @Select("select * from blog.learning_progress where user_id = #{userId} and chapter_id=#{chapterId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "progress", column = "progress"),
    })
    LearningProgress findLearningProgressByUserIdAndChapterId(int userId,int chapterId);

    @Update("""
            update blog.learning_progress set progress   = #{progress},currentTime = #{currentTime}
            where user_id=#{userId} and chapter_id=#{chapterId};
            """)
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "progress", column = "progress"),
            @Result(property = "currentTime", column = "currentTime"),
    })
    boolean updateLearningProgressByUserIdAndChapterId(int userId,int chapterId,int progress,int currentTime);

    @Insert("""
            insert into blog.learning_progress(user_id,chapter_id,resource_id,progress,currentTime) 
            values (#{userId},#{chapterId},#{chapterId},#{progress},#{currentTime});
            """)
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "progress", column = "progress"),
            @Result(property = "currentTime", column = "currentTime"),
    })
    int insertLearningProgressByUserIdAndChapterId(int userId,int chapterId,int progress,int currentTime);

}
