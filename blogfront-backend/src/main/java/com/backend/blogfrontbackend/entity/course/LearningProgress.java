package com.backend.blogfrontbackend.entity.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningProgress {
    private int userId;
    private int chapterId;
    private int resourceId;
    private int progress;
    private int currentTime;
}
