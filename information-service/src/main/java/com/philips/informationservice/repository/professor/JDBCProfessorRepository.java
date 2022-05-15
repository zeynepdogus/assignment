package com.philips.informationservice.repository.professor;

import com.philips.informationservice.builder.ProfessorQueryBuilder;
import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Jdbc Implementation of Professor Repository
 */
@Repository
public class JDBCProfessorRepository implements ProfessorRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ProfessorQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("id", "name", "department_id");

    /**
     * Constructor injection is used
     * @param jdbcTemplate
     * @param queryBuilder
     */
    @Autowired
    public JDBCProfessorRepository(JdbcTemplate jdbcTemplate, ProfessorQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }

    /**
     * Creating the professor using jdbc methods.
     * It takes the professor object and inserts into db
     * @param professor
     * @return
     */
    @Override
    public int createProfessor(Professor professor) {
        List<Object> parameters = Arrays.asList(professor.getId(), professor.getName(), professor.getDepartmentId());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    /**
     * Finds the professor with a professor id
     * @param id
     * @return
     */
    @Override
    public Professor findProfessorById(int id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Professor.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Deletes the course with a professor id
     * @param id
     * @return
     */
    @Override
    public int deleteProfessorById(int id) {
        String query = queryBuilder.buildDeleteByIdQuery(id);
        return jdbcTemplate.update(query);
    }

    /**
     * Finds all professors with their courses
     * @return
     */
    @Override
    public List<ProfessorDetails> findAllProfessors() {
        String query = queryBuilder.buildFindAllProfessors();
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            ProfessorDetails professorDetails = new ProfessorDetails();
            professorDetails.setCourses((String[]) rs.getArray("courses").getArray());
            professorDetails.setName(rs.getString("name"));
            return professorDetails;
        });
    }
}
