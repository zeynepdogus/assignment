package com.philips.informationservice.service.schedule;

import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.repository.schedule.JDBCScheduleRepository;
import com.philips.informationservice.service.schedule.exception.ScheduleExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ScheduleServiceImpl implements ScheduleService {

    private final JDBCScheduleRepository repository;

    @Autowired
    public ScheduleServiceImpl(JDBCScheduleRepository repository) {
        this.repository = repository;
    }

    /**
     * Service method to create a schedule object
     * It @throws ScheduleAlreadyExistsException or ScheduleCreateException if the service fails
     * @param schedule
     */
    @Override
    public void createSchedule(Schedule schedule) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository
                .findSchedule(schedule.getProfessorId(), schedule.getCourseId()));
        if (scheduleOptional.isPresent()) {
            throw new ScheduleExceptionHandler.ScheduleAlreadyExistsException();
        }
        int scheduleToBeAdded = repository.createSchedule(schedule);
        if (scheduleToBeAdded == 0)
            throw new ScheduleExceptionHandler.ScheduleCreateException();
    }

    /**
     * Service method to find the schedule by professor id and course id
     * It @throws ScheduleNotFoundException if the service fails
     * @param professor_id
     * @param course_id
     * @return
     */
    @Override
    public Schedule getSchedule(int professor_id, int course_id) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository.findSchedule(professor_id, course_id));
        return scheduleOptional.orElseThrow(ScheduleExceptionHandler.ScheduleNotFoundException::new);
    }

    /**
     * Service method to schedule by professor id and course id
     * It @throws ScheduleNotFoundException if the service fails
     * @param professor_id
     * @param course_id
     */
    @Override
    public void deleteSchedule(int professor_id, int course_id) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository.findSchedule(professor_id, course_id));
        if (scheduleOptional.isEmpty()) {
            throw new ScheduleExceptionHandler.ScheduleNotFoundException();
        }
        Schedule schedule = scheduleOptional.get();
        repository.deleteSchedule(schedule.getProfessorId(), schedule.getCourseId());
    }
}
