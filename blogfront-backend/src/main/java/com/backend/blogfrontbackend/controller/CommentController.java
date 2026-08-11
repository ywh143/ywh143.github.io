package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.aritcle.Article;
import com.backend.blogfrontbackend.entity.aritcle.Comment;
import com.backend.blogfrontbackend.mapper.CommentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Resource
    CommentMapper commentMapper;

    @PostMapping("/commentInsert")
    public int addByComment(@RequestParam("commentContent") String commentContent,
                            @RequestParam("aidNum") int aidNum,
                            @RequestParam("uidNumLogin") int uidNumLogin) {
        System.out.println("ssdadas");
        // 获取当前系统时间
        LocalDateTime now = LocalDateTime.now();
        // 设置时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 转换为字符串
        String creat = now.format(formatter);
        return commentMapper.addComment(commentContent,creat,aidNum,uidNumLogin);
    }

    @PostMapping("/commentList")
    public List<Comment> getComment(@RequestParam("aidNum") int aidNum){
        return commentMapper.findByAllComment(aidNum);
    }


}
