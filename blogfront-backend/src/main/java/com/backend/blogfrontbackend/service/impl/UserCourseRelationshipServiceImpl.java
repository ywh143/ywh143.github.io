package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.user.UserCourseRelationship;
import com.backend.blogfrontbackend.mapper.UserCourseRelationshipMapper;
import com.backend.blogfrontbackend.service.UserCourseRelationshipService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserCourseRelationshipServiceImpl implements UserCourseRelationshipService {

    @Resource
    UserCourseRelationshipMapper mapper;

    @Override
    public UserCourseRelationship findUserCourseRelationshipMapperByUserIdAndCourseId(int userId, int courseId) {
        UserCourseRelationship userCourseRelationship = mapper.findUserCourseRelationshipMapperByUserIdAndCourseId(userId,courseId);
        if (userCourseRelationship!=null){
            return userCourseRelationship;
        }else{
            int result = mapper.insertUserCourseRelationshipMapperByUserIdAndCourseId(userId,courseId,1,1,1,0);
            System.out.println(result);
            return null;
        }
    }
}
