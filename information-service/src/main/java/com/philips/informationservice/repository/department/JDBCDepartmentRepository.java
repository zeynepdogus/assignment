package com.philips.informationservice.repository.department;

import com.philips.informationservice.builder.DepartmentQueryBuilder;
import com.philips.informationservice.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Jdbc Implementation of Department Repository
 */
@Repository
public class JDBCDepartmentRepository implements DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final DepartmentQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("id", "name");

    /**
     * Constructor injection is used
     * @param jdbcTemplate
     * @param queryBuilder
     */
    @Autowired
    public JDBCDepartmentRepository(JdbcTemplate jdbcTemplate, DepartmentQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }

    /**
     * Creating the department using jdbc methods.
     * It takes the department object and inserts into db
     * @param department
     * @return
     */
    @Override
    public int createDepartment(Department department) {
        List<Object> parameters = Arrays.asList(department.getId(), department.getName());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    /**
     * Finds the course with a department id
     * @param id
     * @return
     */
    @Override
    public Department findDepartmentById(int id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Department.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * Deletes the course with a department id
     * @param id
     * @return
     */
    @Override
    public int deleteDepartmentById(int id) {
        String query = queryBuilder.buildDeleteByIdQuery(id);
        return jdbcTemplate.update(query);
    }
}
