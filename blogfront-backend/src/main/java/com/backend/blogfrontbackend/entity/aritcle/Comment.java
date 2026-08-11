package com.backend.blogfrontbackend.entity.aritcle;

import com.backend.blogfrontbackend.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//生成无参构造方法
@AllArgsConstructor
public class Comment {
    private int id;
    private String content;
    private String creat;
    private int articleId;
    private int userId;
    private User user;
}
