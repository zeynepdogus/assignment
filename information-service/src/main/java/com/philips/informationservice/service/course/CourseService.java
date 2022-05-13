package com.philips.informationservice.service.course;

import com.philips.informationservice.model.Course;

public interface CourseService {

    public void createCourse(Course course);
    public Course getCourse(int id);
    public void deleteCourse(int id);
}
