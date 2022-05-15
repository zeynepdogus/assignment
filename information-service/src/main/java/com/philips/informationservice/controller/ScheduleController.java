package com.philips.informationservice.controller;

import com.philips.informationservice.model.Schedule;
import com.philips.informationservice.service.schedule.ScheduleService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for Courses
 */
@RestController
@RequestMapping("/api/v1/schedule-management")
@RequiredArgsConstructor
public class ScheduleController {

    @Autowired
    public ScheduleService scheduleService;

    /**
     * Creates a rest endpoint to create a new schedule
     * Api responses are set for Swagger documentation
     * @param schedule
     * @return
     */
    @PostMapping("/schedules")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "Schedule already exists."),
            @ApiResponse(code = 500, message = "Schedule could not be created."),
            @ApiResponse(code = 404, message = "Schedule not found.")})
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        scheduleService.createSchedule(schedule);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    /**
     * Creates a rest endpoint to get a professor with professor id and course id.
     * Api responses are set for Swagger documentation
     * @param professor_id
     * @param course_id
     * @return
     */
    @GetMapping("/schedules/{professor_id}/{course_id}")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Schedule not found.")})
    public ResponseEntity<Schedule> getSchedule(@PathVariable("professor_id") int professor_id,
                                                @PathVariable("course_id") int course_id) {
        Schedule course = scheduleService.getSchedule(professor_id, course_id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * Creates a rest endpoint to delete a professor id and course id.
     * Api responses are set for Swagger documentation
     * @param professor_id
     * @param course_id
     * @return
     */
    @DeleteMapping("/schedules/{professor_id}/{course_id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Nothing to delete."),
            @ApiResponse(code = 404, message = "Schedule not found.")})
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("professor_id") int professor_id,
                                                     @PathVariable("course_id") int course_id) {
        scheduleService.deleteSchedule(professor_id, course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
