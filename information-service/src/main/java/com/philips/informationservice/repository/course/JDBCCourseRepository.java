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

/**
 * Jdbc Implementation of Course Repository
 */
@Repository
public class JDBCCourseRepository implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    private final CourseQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("id", "name", "department_id", "credits");

    /**
     * Constructor injection is used
     * @param jdbcTemplate
     * @param queryBuilder
     */
    @Autowired
    public JDBCCourseRepository(JdbcTemplate jdbcTemplate, CourseQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }

    /**
     * Creating the course using jdbc methods. It takes the course object and inserts into db
     * @param course
     * @return
     */
    @Override
    public int createCourse(Course course) {
        List<Object> parameters = Arrays.asList(course.getId(), course.getName(),
                course.getDepartmentId(), course.getCredits());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    /**
     * Finds the course with a course id
     * @param id
     * @return
     */
    @Override
    public Course findCourseById(int id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Course.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * Deletes the course with a course id
     * @param id
     * @return
     */
    @Override
    public int deleteCourseById(int id) {
        String query = queryBuilder.buildDeleteByIdQuery(id);
        return jdbcTemplate.update(query);
    }
}
