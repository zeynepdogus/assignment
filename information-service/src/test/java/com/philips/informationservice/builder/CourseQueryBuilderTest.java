package com.philips.informationservice.builder;

import com.philips.informationservice.model.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseQueryBuilderTest {

    @InjectMocks
    CourseQueryBuilder queryBuilder;

    private List<String> columns;

    private List<Object> parameters;

    private Course course;

    @Before
    public void setUp() {
        course = new Course();
        course.setName("Test");
        course.setId(1);
        course.setCredits(13);
        course.setDepartmentId(2);

        columns = Arrays.asList("id", "name", "department_id", "credits");
        parameters = Arrays.asList(course.getId(), course.getName(),
                course.getDepartmentId(), course.getCredits());
    }

    @Test
    public void whenCreateCourse() {
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        Assert.assertTrue(query.startsWith("INSERT INTO"));
        Assert.assertTrue(query.contains("COURSE"));
    }

    @Test
    public void whenGetCourse() {
        String query = queryBuilder.buildFindByIdQuery(course.getId());
        Assert.assertTrue(query.startsWith("SELECT"));
        Assert.assertTrue(query.contains("COURSE"));
    }

    @Test
    public void whenDeleteCourse() {
        String query = queryBuilder.buildDeleteByIdQuery(course.getId());
        Assert.assertTrue(query.startsWith("DELETE"));
        Assert.assertTrue(query.contains("COURSE"));
    }
}
