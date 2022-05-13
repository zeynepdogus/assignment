package com.philips.informationservice.service.schedule;

import com.philips.informationservice.model.Schedule;

public interface ScheduleService {
    public void createSchedule(Schedule schedule);
    public Schedule getSchedule(int professor_id, int course_id);
    public void deleteSchedule(int professor_id, int course_id);
}
