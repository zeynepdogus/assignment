package com.philips.informationservice.builder;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorQueryBuilderTest {

    @InjectMocks
    ProfessorQueryBuilder queryBuilder;

    private List<String> columns;

    private List<Object> parameters;

    private Professor professor;

    @Before
    public void setUp() {
        professor = new Professor();
        professor.setName("Test");
        professor.setId(1);
        professor.setDepartmentId(2);

        columns = Arrays.asList("id", "name", "department_id");
        parameters = Arrays.asList(professor.getId(), professor.getName(),
                professor.getDepartmentId());
    }

    @Test
    public void whenCreateProfessor() {
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        Assert.assertTrue(query.startsWith("INSERT INTO"));
        Assert.assertTrue(query.contains("PROFESSOR"));
    }

    @Test
    public void whenGetAllProfessors() {
        String query = queryBuilder.buildFindAllProfessors();
        Assert.assertTrue(query.startsWith("SELECT"));
        Assert.assertTrue(query.contains("GROUP BY"));
    }

    @Test
    public void whenDeleteProfessor() {
        String query = queryBuilder.buildDeleteByIdQuery(professor.getId());
        Assert.assertTrue(query.startsWith("DELETE"));
        Assert.assertTrue(query.contains("PROFESSOR"));
    }

    @Test
    public void whenGetProfessor() {
        String query = queryBuilder.buildFindByIdQuery(professor.getId());
        Assert.assertTrue(query.startsWith("SELECT"));
        Assert.assertTrue(query.contains("PROFESSOR"));
    }
}
