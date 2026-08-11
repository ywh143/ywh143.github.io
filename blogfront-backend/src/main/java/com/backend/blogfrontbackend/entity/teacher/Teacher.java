package com.backend.blogfrontbackend.entity.teacher;

import com.backend.blogfrontbackend.entity.user.UserAccount;
import lombok.Data;

@Data
public class Teacher {
    private long teacherId;
    private String name;
    private String gender;
    private long age;
    private UserAccount userAccount;
    private String graduationSchool;
}
