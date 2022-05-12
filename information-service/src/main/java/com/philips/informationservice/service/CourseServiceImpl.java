package com.philips.informationservice.service;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.repository.JdbcInformationRepository;
import com.philips.informationservice.service.exception.CourseAlreadyExistsException;
import com.philips.informationservice.service.exception.CourseCreateException;
import com.philips.informationservice.service.exception.CourseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseServiceImpl implements CourseService {

    @Autowired
    private JdbcInformationRepository repository;

    @Override
    public void createCourse(Course course) {
        Optional<Course> courseById = Optional.ofNullable(repository.findCourseById(course.getId()));
        if (courseById.isPresent()) {
            throw new CourseAlreadyExistsException();
        }
        int courseToBeAdded = repository.createCourse(course);
        if(courseToBeAdded == 0)
            throw new CourseCreateException();
    }

    @Override
    public Course getCourse(int id) {
        Optional<Course> courseOptional = Optional.ofNullable(repository.findCourseById(id));
        return courseOptional.orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public void deleteCourse(int id) {
        Optional<Course> courseById = Optional.ofNullable(repository.findCourseById(id));
        if (courseById.isEmpty()) {
            throw new CourseNotFoundException();
        }
        Course car = courseById.get();
        repository.deleteCourseById(car.getId());

    }
}
