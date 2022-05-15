package com.philips.informationservice.builder;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.model.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentQueryBuilderTest {

    @InjectMocks
    DepartmentQueryBuilder queryBuilder;

    private List<String> columns;

    private List<Object> parameters;

    private Department department;

    @Before
    public void setUp() {
        department = new Department();
        department.setName("Test");
        department.setId(1);

        columns = Arrays.asList("id", "name");
        parameters = Arrays.asList(department.getId(), department.getName());
    }

    @Test
    public void whenCreateDepartment() {
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        Assert.assertTrue(query.startsWith("INSERT INTO"));
        Assert.assertTrue(query.contains("DEPARTMENT"));
    }

    @Test
    public void whenGetDepartment() {
        String query = queryBuilder.buildFindByIdQuery(department.getId());
        Assert.assertTrue(query.startsWith("SELECT"));
        Assert.assertTrue(query.contains("DEPARTMENT"));
    }

    @Test
    public void whenDeleteDepartment() {
        String query = queryBuilder.buildDeleteByIdQuery(department.getId());
        Assert.assertTrue(query.startsWith("DELETE"));
        Assert.assertTrue(query.contains("DEPARTMENT"));
    }
}
