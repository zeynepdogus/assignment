package com.philips.informationservice.controller;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.repository.course.JDBCCourseRepository;
import com.philips.informationservice.service.course.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseServiceImpl courseService;

    @MockBean
    private JDBCCourseRepository repository;

    @Autowired
    private JacksonTester<Course> json;

    private Course course;

    @Before
    public void setUp() {
        course = new Course();
        course.setName("Test");
        course.setId(1);
        course.setCredits(13);
        course.setDepartmentId(2);
    }

    /**
     * Tests for successful creation of new course in the system
     */
    @Test
    public void whenCreateCourse() throws Exception {
        Mockito.when(repository.findCourseById(course.getId())).thenReturn(null);
        Mockito.when(repository.createCourse(course)).thenReturn(1);
        courseService.createCourse(course);
        mvc.perform(
                post(new URI("/api/v1/course-management/courses"))
                        .content(json.write(course).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetCourse() throws Exception {
        Mockito.when(repository.findCourseById(course.getId())).thenReturn(course);

        courseService.getCourse(this.course.getId());

        mvc.perform(get(new URI("/api/v1/course-management/courses/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(course).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteCourse() throws Exception {
        Mockito.when(repository.findCourseById(course.getId())).thenReturn(course);
        Mockito.when(repository.deleteCourseById(course.getId())).thenReturn(1);

        courseService.deleteCourse(course.getId());
        mvc.perform(delete(new URI("/api/v1/course-management/courses/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(course).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
