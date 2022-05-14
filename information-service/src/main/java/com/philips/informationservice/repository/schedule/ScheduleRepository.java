package com.philips.informationservice.repository.schedule;

import com.philips.informationservice.model.Schedule;

public interface ScheduleRepository {

    int createSchedule(Schedule schedule);
    Schedule findSchedule(int professor_id, int course_id);
    int deleteSchedule(int professor_id, int course_id);
}
