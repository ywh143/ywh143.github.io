package com.backend.blogfrontbackend.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseRelationship {
    private int userId;
    private int courseId;
    private int favoriteStatus;
    private int purchaseStatus;
    private int learningStatus;
    private int learningProgress;
}
