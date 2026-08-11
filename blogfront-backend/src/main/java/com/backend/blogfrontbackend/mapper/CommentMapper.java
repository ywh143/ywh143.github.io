package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.aritcle.Article;
import com.backend.blogfrontbackend.entity.aritcle.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    //添加文章信息
    @Insert("insert into blog.comment(content,creat,article_id,user_id) " +
            "values(#{contentComment},#{creat},#{aidNum},#{uidNumLogin})")
    int addComment(String contentComment,String creat,int aidNum,int uidNumLogin);

    //查询所有文章信息
    @Select("select * from blog.comment,blog.user where article_id = #{aidNum} and comment.user_id = user.user_id")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "creat",property = "creat"),
            @Result(column = "userId",property = "userId"),
            @Result(column = "articleId",property = "articleId"),
//            @Result(column = "user_id",property = "user.userId"),
            @Result(column = "name",property = "user.name")
    })
    List<Comment> findByAllComment(int aidNum);

}
