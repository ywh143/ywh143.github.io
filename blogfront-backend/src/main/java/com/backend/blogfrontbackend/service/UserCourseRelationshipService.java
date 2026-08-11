package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.user.UserCourseRelationship;

public interface UserCourseRelationshipService {

    UserCourseRelationship findUserCourseRelationshipMapperByUserIdAndCourseId(int userId, int courseId);

}
