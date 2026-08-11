package com.backend.blogfrontbackend.entity.course;

import com.backend.blogfrontbackend.entity.teacher.Teacher;
import lombok.Data;

@Data
public class Course {
    private long courseId;
    private String courseName;
    private String courseDescription;
    private double coursePrice;
    private Teacher teacher;
    private long onlineStatus;
    private String courseCover;
}
