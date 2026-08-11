package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.user.UserCourseRelationship;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserCourseRelationshipMapper {

    @Select("select * from blog.user_course_relationship where user_id = #{userId} and course_id=#{courseId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "courseId", column = "course_Id"),
            @Result(property = "favoriteStatus", column = "favorite_status"),
            @Result(property = "purchaseStatus", column = "purchase_status"),
            @Result(property = "learningStatus", column = "learning_status"),
            @Result(property = "learningProgress", column = "learning_progress"),
    })
    UserCourseRelationship findUserCourseRelationshipMapperByUserIdAndCourseId(int userId, int courseId);

    @Insert("""
            insert into blog.user_course_relationship(user_id,course_id,favorite_status,purchase_status,learning_status,learning_progress) 
            values ( #{userId},#{courseId},#{favoriteStatus},#{purchaseStatus},#{learningStatus},#{learningProgress});
            """)
    int insertUserCourseRelationshipMapperByUserIdAndCourseId(
            int userId,
            int courseId,
            int favoriteStatus,
            int purchaseStatus,
            int learningStatus,
            int learningProgress);


}
