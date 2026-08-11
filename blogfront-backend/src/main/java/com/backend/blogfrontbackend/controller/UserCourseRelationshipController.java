package com.backend.blogfrontbackend.controller;


import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.entity.user.UserCourseRelationship;
import com.backend.blogfrontbackend.service.UserCourseRelationshipService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/UserCourseRelationship")
public class UserCourseRelationshipController {

    @Resource
    UserCourseRelationshipService service;

    @PostMapping("userCourseRelationship")
    public RestBean<UserCourseRelationship> findUserCourseRelationshipMapperByUserIdAndCourseId(
                                                    @RequestParam("userId") int userId,
                                                    @RequestParam("courseId") int courseId) {
        UserCourseRelationship userCourseRelationship = service.findUserCourseRelationshipMapperByUserIdAndCourseId(userId,courseId);
        System.out.println("123123"+userCourseRelationship);
        return RestBean.success(userCourseRelationship);
    }

}
