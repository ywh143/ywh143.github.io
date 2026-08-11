package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.course.LearningProgress;
import com.backend.blogfrontbackend.mapper.LearningProgressMapper;
import com.backend.blogfrontbackend.service.LearningProgressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LearningProgressServiceImpl implements LearningProgressService {

    @Resource
    LearningProgressMapper mapper;

    @Override
    public LearningProgress findLearningProgressByUserIdAndChapterId(int userId, int chapterId) {
        System.out.println(mapper.findLearningProgressByUserIdAndChapterId(userId,chapterId));
        return mapper.findLearningProgressByUserIdAndChapterId(userId,chapterId);
    }

    @Override
    public boolean updateLearningProgressByUserIdAndChapterId(int userId, int chapterId, int progress,int currentTime) {
        return mapper.updateLearningProgressByUserIdAndChapterId(userId,chapterId,progress,currentTime);
    }

    @Override
    public int insertLearningProgressByUserIdAndChapterId(int userId, int chapterId, int progress,int currentTime) {
        return mapper.insertLearningProgressByUserIdAndChapterId(userId,chapterId,progress,currentTime);
    }
}
