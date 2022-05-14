package com.philips.informationservice.service;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.repository.course.JDBCCourseRepository;
import com.philips.informationservice.service.course.CourseServiceImpl;
import com.philips.informationservice.service.course.exception.CourseExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Mock
    private JDBCCourseRepository repository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;

    @Before
    public void setUp() {
        course = new Course();
        course.setName("Test");
        course.setId(1);
        course.setCredits(13);
        course.setDepartmentId(2);
    }

    @Test
    public void whenSaveCourse_shouldReturnCourse() {
        when(repository.findCourseById(course.getId())).thenReturn(null);
        when(repository.createCourse(course)).thenReturn(1);

        courseService.createCourse(course);
        verify(repository).createCourse(course);
    }

    @Test(expected = CourseExceptionHandler.CourseAlreadyExistsException.class)
    public void whenSaveCourse_shouldReturnCourseExists() {
        when(repository.findCourseById(course.getId())).thenReturn(course);
        when(repository.createCourse(course)).thenReturn(0);

        courseService.createCourse(course);
    }

    @Test(expected = CourseExceptionHandler.CourseCreateException.class)
    public void whenSaveCourse_shouldReturnInternalError() {
        when(repository.findCourseById(course.getId())).thenReturn(null);
        when(repository.createCourse(course)).thenReturn(0);

        courseService.createCourse(course);
        verify(repository).createCourse(course);
    }

    @Test
    public void whenGetCourse_shouldReturnCourseWithId() {
        when(repository.findCourseById(course.getId())).thenReturn(course);

        Course courseReturned = courseService.getCourse(this.course.getId());

        Assert.assertEquals(courseReturned, course);
        verify(repository).findCourseById(course.getId());
    }

    @Test(expected = CourseExceptionHandler.CourseNotFoundException.class)
    public void whenGetCourse_shouldReturnCourseNotFound() {
        when(repository.findCourseById(course.getId())).thenReturn(null);

        courseService.getCourse(this.course.getId());
    }

    @Test
    public void whenDeleteCourse_shouldDeleteCourse() {
        when(repository.findCourseById(course.getId())).thenReturn(course);
        when(repository.deleteCourseById(course.getId())).thenReturn(1);

        courseService.deleteCourse(course.getId());
        verify(repository).deleteCourseById(course.getId());
    }

    @Test(expected = CourseExceptionHandler.CourseNotFoundException.class)
    public void whenDeleteCourse_shouldReturnCourseNotFound() {
        when(repository.findCourseById(course.getId())).thenReturn(null);
        when(repository.deleteCourseById(course.getId())).thenReturn(0);

        courseService.deleteCourse(course.getId());
        verify(repository).deleteCourseById(course.getId());
    }
}
