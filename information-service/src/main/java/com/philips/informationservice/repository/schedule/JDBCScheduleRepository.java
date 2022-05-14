package com.philips.informationservice.repository.schedule;

import com.philips.informationservice.builder.ScheduleQueryBuilder;
import com.philips.informationservice.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class JDBCScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ScheduleQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("professor_id", "course_id", "semester", "year");

    @Autowired
    public JDBCScheduleRepository(JdbcTemplate jdbcTemplate, ScheduleQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }


    @Override
    public int createSchedule(Schedule schedule) {
        List<Object> parameters = Arrays.asList(schedule.getProfessorId(), schedule.getCourseId(),
                schedule.getSemester(), schedule.getYear());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    @Override
    public Schedule findSchedule(int professor_id, int course_id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(professor_id, course_id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Schedule.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteSchedule(int professor_id, int course_id) {
        String query = queryBuilder.buildDeleteByIdQuery(professor_id, course_id);
        return jdbcTemplate.update(query);
    }
}
