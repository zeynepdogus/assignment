package com.philips.informationservice.controller;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.service.course.CourseService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course-management")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    public CourseService courseService;

    @PostMapping("/courses")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "Course already exists."),
            @ApiResponse(code = 500, message = "Course could not be created."),
            @ApiResponse(code = 404, message = "Course not found.")})
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Course not found.")})
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {
        Course course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Nothing to delete."), @ApiResponse(code = 404, message = "Course not found.")})
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
