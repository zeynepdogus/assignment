package com.philips.informationservice.controller;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.repository.professor.JDBCProfessorRepository;
import com.philips.informationservice.repository.schedule.JDBCScheduleRepository;
import com.philips.informationservice.service.professor.ProfessorServiceImpl;
import com.philips.informationservice.service.schedule.ScheduleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleServiceImpl scheduleService;

    @MockBean
    private JDBCScheduleRepository repository;

    @Autowired
    private JacksonTester<Schedule> json;

    private Schedule schedule;

    @Before
    public void setUp() {
        schedule = new Schedule();
        schedule.setProfessorId(1);
        schedule.setCourseId(2);
        schedule.setSemester(3);
        schedule.setYear(2022);
    }

    @Test
    public void whenCreateSchedule() throws Exception {
        Mockito.when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(null);
        Mockito.when(repository.createSchedule(schedule)).thenReturn(1);
        scheduleService.createSchedule(schedule);
        mvc.perform(
                post(new URI("/api/v1/schedule-management/schedules"))
                        .content(json.write(schedule).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetSchedule() throws Exception {
        Mockito.when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(schedule);

        scheduleService.getSchedule(schedule.getProfessorId(), schedule.getCourseId());

        mvc.perform(get(new URI("/api/v1/schedule-management/schedules/1/2"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(schedule).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteSchedule() throws Exception {
        Mockito.when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(schedule);
        Mockito.when(repository.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(1);

        scheduleService.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
        mvc.perform(delete(new URI("/api/v1/schedule-management/schedules/1/2"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(schedule).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
