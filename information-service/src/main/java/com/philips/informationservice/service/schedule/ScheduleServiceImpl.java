package com.philips.informationservice.service.schedule;

import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.repository.JdbcInformationRepository;
import com.philips.informationservice.service.schedule.exception.ScheduleAlreadyExistsException;
import com.philips.informationservice.service.schedule.exception.ScheduleCreateException;
import com.philips.informationservice.service.schedule.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private JdbcInformationRepository repository;

    @Override
    public void createSchedule(Schedule schedule) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository
                .findSchedule(schedule.getProfessor_id(), schedule.getCourse_id()));
        if (scheduleOptional.isPresent()) {
            throw new ScheduleAlreadyExistsException();
        }
        int scheduleToBeAdded = repository.createSchedule(schedule);
        if(scheduleToBeAdded == 0)
            throw new ScheduleCreateException();
    }

    @Override
    public Schedule getSchedule(int professor_id, int course_id) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository.findSchedule(professor_id, course_id));
        return scheduleOptional.orElseThrow(ScheduleNotFoundException::new);
    }

    @Override
    public void deleteSchedule(int professor_id, int course_id) {
        Optional<Schedule> scheduleOptional = Optional.ofNullable(repository.findSchedule(professor_id, course_id));
        if (scheduleOptional.isEmpty()) {
            throw new ScheduleNotFoundException();
        }
        Schedule schedule = scheduleOptional.get();
        repository.deleteSchedule(schedule.getProfessor_id(), schedule.getCourse_id());
    }
}
