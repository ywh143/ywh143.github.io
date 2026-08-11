package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.course.LearningProgress;

public interface LearningProgressService {

    LearningProgress findLearningProgressByUserIdAndChapterId(int userId, int chapterId);

    boolean updateLearningProgressByUserIdAndChapterId(int userId,int chapterId,int progress,int currentTime);

    int insertLearningProgressByUserIdAndChapterId(int userId,int chapterId,int progress,int currentTime);
}
