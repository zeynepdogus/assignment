package com.philips.informationservice.repository.course;

import com.philips.informationservice.builder.CourseQueryBuilder;
import com.philips.informationservice.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class JDBCCourseRepository implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    private final CourseQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("id", "name", "department_id", "credits");

    @Autowired
    public JDBCCourseRepository(JdbcTemplate jdbcTemplate, CourseQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public int createCourse(Course course) {
        List<Object> parameters = Arrays.asList(course.getId(), course.getName(),
                course.getDepartmentId(), course.getCredits());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    @Override
    public Course findCourseById(int id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Course.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int deleteCourseById(int id) {
        String query = queryBuilder.buildDeleteByIdQuery(id);
        return jdbcTemplate.update(query);
    }
}
