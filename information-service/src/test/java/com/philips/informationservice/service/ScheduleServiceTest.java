package com.philips.informationservice.service;

import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.repository.schedule.JDBCScheduleRepository;
import com.philips.informationservice.service.schedule.ScheduleServiceImpl;
import com.philips.informationservice.service.schedule.exception.ScheduleExceptionHandler;
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
public class ScheduleServiceTest {
    @Mock
    private JDBCScheduleRepository repository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

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
    public void whenSaveSchedule_shouldReturnSchedule() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(null);
        when(repository.createSchedule(schedule)).thenReturn(1);

        scheduleService.createSchedule(schedule);
        verify(repository).createSchedule(schedule);
    }

    @Test(expected = ScheduleExceptionHandler.ScheduleAlreadyExistsException.class)
    public void whenSaveSchedule_shouldReturnScheduleExists() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(schedule);
        when(repository.createSchedule(schedule)).thenReturn(0);

        scheduleService.createSchedule(schedule);
    }

    @Test(expected = ScheduleExceptionHandler.ScheduleCreateException.class)
    public void whenSaveSchedule_shouldReturnInternalError() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(null);
        when(repository.createSchedule(schedule)).thenReturn(0);

        scheduleService.createSchedule(schedule);
        verify(repository).createSchedule(schedule);
    }

    @Test
    public void whenGetSchedule_shouldReturnScheduleWithId() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(schedule);

        Schedule scheduleReturned = scheduleService.getSchedule(schedule.getProfessorId(), schedule.getCourseId());

        Assert.assertEquals(scheduleReturned, schedule);
        verify(repository).findSchedule(schedule.getProfessorId(), schedule.getCourseId());
    }

    @Test(expected = ScheduleExceptionHandler.ScheduleNotFoundException.class)
    public void whenGetSchedule_shouldReturnScheduleNotFound() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(null);

        scheduleService.getSchedule(schedule.getProfessorId(), schedule.getCourseId());
    }

    @Test
    public void whenDeleteSchedule_shouldDeleteSchedule() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(schedule);
        when(repository.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(1);

        scheduleService.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
        verify(repository).deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
    }

    @Test(expected = ScheduleExceptionHandler.ScheduleNotFoundException.class)
    public void whenDeleteSchedule_shouldReturnScheduleNotFound() {
        when(repository.findSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(null);
        when(repository.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId())).thenReturn(0);

        scheduleService.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
        verify(repository).deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
    }
}
