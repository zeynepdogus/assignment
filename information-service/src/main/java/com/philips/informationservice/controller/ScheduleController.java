package com.philips.informationservice.controller;

import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    @Autowired
    public ScheduleService scheduleService;

    @PostMapping("/create")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule course) {
        scheduleService.createSchedule(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/get/{professor_id}/{course_id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable("professor_id") int professor_id,
                                                @PathVariable("course_id") int course_id) {
        Schedule course = scheduleService.getSchedule(professor_id, course_id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{professor_id}/{course_id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("professor_id") int professor_id,
                                                     @PathVariable("course_id") int course_id) {
        scheduleService.deleteSchedule(professor_id, course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
