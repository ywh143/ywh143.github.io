package com.backend.blogfrontbackend.entity.course;

import lombok.Data;

@Data
public class CourseResource {
    private long resourceId;
    private int chapterId;
    private String resourceType;
    private String resourceUrl;
    private long sortOrder;
}
