package com.backend.blogfrontbackend.entity.course;

import lombok.Data;

@Data
public class Chapter {
    private long chapterId;
    private Course course;
    private String chapterTitle;
    private int sortOrder;
}
