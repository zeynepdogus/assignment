package com.philips.informationservice.repository.course;

import com.philips.informationservice.model.Course;

public interface CourseRepository {
    int createCourse(Course course);
    Course findCourseById(int id);
    int deleteCourseById(int id);
}
