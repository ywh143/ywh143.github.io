package com.backend.blogfrontbackend.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private String gender;
    private int age;
    private String school;
    private String identity;
    private String userAccountUserName;
}
