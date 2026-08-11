package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.course.CourseResource;
import com.backend.blogfrontbackend.entity.course.LearningProgress;
import com.backend.blogfrontbackend.service.CourseResourceService;
import com.backend.blogfrontbackend.service.LearningProgressService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning")
public class LearningProgressController {

    @Resource
    LearningProgressService learningProgressService;

    @PostMapping ("/findLearningProgress")
    RestBean<LearningProgress> findLearningProgressByUserIdAndChapterId(@RequestParam("userId") int userId,
                                                                        @RequestParam("chapterId") int chapterId
                                                           ) {
        LearningProgress learning = learningProgressService.findLearningProgressByUserIdAndChapterId(userId, chapterId);

        if (learning==null){
            return RestBean.failure(400);
        }
        System.out.println(learning);
        return RestBean.success(learning);
    }

    @PostMapping("/updateLearningProgress")
    RestBean<String> updateLearningProgressByUserIdAndChapterId(@RequestParam("userId") int userId,
                                                                @RequestParam("chapterId") int chapterId,
                                                                @RequestParam("progress") double progress,
                                                                @RequestParam("currentTime") double currentTime
                                                                ) {

        int progressInt = (int)(progress*100);
        boolean flag = learningProgressService.updateLearningProgressByUserIdAndChapterId(userId,chapterId,progressInt,(int)(currentTime));
        System.out.println(flag);
        return RestBean.success();
    }

    @PostMapping("/insertLearningProgress")
    RestBean<String> insertLearningProgressByUserIdAndChapterId(@RequestParam("userId") int userId,
                                                                @RequestParam("chapterId") int chapterId) {

        int progressInt = 0;
        int currentTime = 0;
        int result = learningProgressService.insertLearningProgressByUserIdAndChapterId(userId,chapterId,progressInt,currentTime);
        System.out.println(result);
        return RestBean.success();
    }

}
