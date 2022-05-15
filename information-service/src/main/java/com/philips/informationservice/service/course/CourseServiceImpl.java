package com.philips.informationservice.service.course;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.repository.course.JDBCCourseRepository;
import com.philips.informationservice.service.course.exception.CourseExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CourseServiceImpl implements CourseService {

    private final JDBCCourseRepository repository;

    @Autowired
    public CourseServiceImpl(JDBCCourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Service method to create a course object
     * It @throws CourseAlreadyExistsException or CourseCreateException if the service fails
     * @param course
     */
    @Override
    public void createCourse(Course course) {
        Optional<Course> courseById = Optional.ofNullable(repository.findCourseById(course.getId()));
        if (courseById.isPresent()) {
            throw new CourseExceptionHandler.CourseAlreadyExistsException();
        }
        int courseToBeAdded = repository.createCourse(course);
        if (courseToBeAdded == 0)
            throw new CourseExceptionHandler.CourseCreateException();
    }

    /**
     * Service method to find the course by id
     * It @throws CourseNotFoundException if the service fails
     * @param id
     * @return
     */
    @Override
    public Course getCourse(int id) {
        Optional<Course> courseOptional = Optional.ofNullable(repository.findCourseById(id));
        return courseOptional.orElseThrow(CourseExceptionHandler.CourseNotFoundException::new);
    }

    /**
     * Service method to delete course by id
     * It @throws CourseNotFoundException if the service fails
     * @param id
     */
    @Override
    public void deleteCourse(int id) {
        Optional<Course> courseById = Optional.ofNullable(repository.findCourseById(id));
        if (courseById.isEmpty()) {
            throw new CourseExceptionHandler.CourseNotFoundException();
        }
        Course course = courseById.get();
        repository.deleteCourseById(course.getId());

    }

}
