package com.backend.blogfrontbackend.entity.teacher;

import com.backend.blogfrontbackend.entity.course.Course;
import lombok.Data;

@Data
public class Teach {
    private Teacher teacher;
    private Course course;
}
