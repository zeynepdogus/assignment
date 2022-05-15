package com.philips.informationservice.builder;

import com.philips.informationservice.model.Schedule;
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
public class ScheduleQueryBuilderTest {

    @InjectMocks
    ScheduleQueryBuilder queryBuilder;

    private List<String> columns;

    private List<Object> parameters;

    private Schedule schedule;

    @Before
    public void setUp() {
        schedule = new Schedule();
        schedule.setProfessorId(1);
        schedule.setCourseId(2);
        schedule.setSemester(3);
        schedule.setYear(2022);

        columns = Arrays.asList("professor_id", "course_id", "semester", "year");
        parameters = Arrays.asList(schedule.getProfessorId(), schedule.getCourseId(),
                schedule.getSemester(), schedule.getYear());
    }

    @Test
    public void whenCreateSchedule() {
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        Assert.assertTrue(query.startsWith("INSERT INTO"));
        Assert.assertTrue(query.contains("SCHEDULE"));
    }

    @Test
    public void whenGetSchedule() {
        String query = queryBuilder.buildFindByIdQuery(schedule.getProfessorId(), schedule.getCourseId());
        Assert.assertTrue(query.startsWith("SELECT"));
        Assert.assertTrue(query.contains("SCHEDULE"));
    }

    @Test
    public void whenDeleteSchedule() {
        String query = queryBuilder.buildDeleteByIdQuery(schedule.getProfessorId(), schedule.getCourseId());
        Assert.assertTrue(query.startsWith("DELETE"));
        Assert.assertTrue(query.contains("SCHEDULE"));
    }
}
