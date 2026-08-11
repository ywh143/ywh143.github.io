package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.course.Course;
import com.backend.blogfrontbackend.entity.teacher.Teacher;
import com.backend.blogfrontbackend.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Resource
    CourseService courseService;

    @GetMapping("/Courses")
    RestBean<List<Course>> findAllCourse() {
        List<Course> list = courseService.findAllCourse();
        return RestBean.success(list);
    }

    @GetMapping("/Courses/{id}")
    RestBean<Course> findCourseById(@PathVariable("id") String id) {
        Course course = courseService.findCourseById(Integer.parseInt(id));
        return RestBean.success(course);
    }

    @GetMapping("/Course/{name}")
    RestBean<Course> findCourseByName(@PathVariable("name") String name) {
        Course course = courseService.findCourseByName(name);
        return RestBean.success(course);
    }

    @PostMapping("/insertCourse")
    RestBean<String> insertCourse(@RequestParam("courseName") String courseName,
                                  @RequestParam("courseDescription") String courseDescription,
                                  @RequestParam("coursePrice") Double coursePrice,
                                  @RequestParam("teacherId") int teacherId,
                                  @RequestParam("onlineStatus") int onlineStatus) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setCoursePrice(coursePrice);
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        course.setTeacher(teacher);
        course.setOnlineStatus(onlineStatus);
        if (courseService.insertCourse(course)) {
            return RestBean.success("添加成功");
        } else {
            return RestBean.failure(404, "添加失败");
        }
    }

    @GetMapping("/deleteCourse/{id}")
    RestBean<String> deleteCourse(@PathVariable("id") int id) {
        if (courseService.deleteCourse(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @PostMapping("/updateCourse")
    RestBean<String> updateCourse(@RequestParam("courseName") String courseName,
                                  @RequestParam("courseDescription") String courseDescription,
                                  @RequestParam("coursePrice") Double coursePrice,
                                  @RequestParam("teacherId") int teacherId,
                                  @RequestParam("onlineStatus") int onlineStatus) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setCoursePrice(coursePrice);
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        course.setTeacher(teacher);
        course.setOnlineStatus(onlineStatus);
        if (courseService.updateCourse(course)) {
            return RestBean.success("修改成功");
        } else {
            return RestBean.failure(404, "修改失败");
        }
    }

    @PostMapping("/updateCourseStatus/{id}")
    public void updateCourseStatus(@PathVariable("id") int id
            , @RequestParam("onlineStatus") int status) {
       courseService.updateCourseStatus(id,status);
    }
}
