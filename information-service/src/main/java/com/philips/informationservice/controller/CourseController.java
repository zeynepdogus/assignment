package com.philips.informationservice.controller;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-management")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    public CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {
        Course course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
