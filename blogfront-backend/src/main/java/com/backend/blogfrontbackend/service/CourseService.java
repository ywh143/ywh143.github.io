package com.backend.blogfrontbackend.service;


import com.backend.blogfrontbackend.entity.course.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourse();
    Course findCourseById(int id);
    Course findCourseByName(String name);
    boolean insertCourse(Course course);
    boolean deleteCourse(int id);
    boolean updateCourse(Course course);
    boolean updateCourseStatus(int id, int status);
}
