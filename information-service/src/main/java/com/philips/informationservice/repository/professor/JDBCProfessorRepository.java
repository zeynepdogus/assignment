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

@Repository
public class JDBCProfessorRepository implements ProfessorRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ProfessorQueryBuilder queryBuilder;

    private final List<String> columns = Arrays.asList("id", "name", "department_id");

    @Autowired
    public JDBCProfessorRepository(JdbcTemplate jdbcTemplate, ProfessorQueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public int createProfessor(Professor professor) {
        List<Object> parameters = Arrays.asList(professor.getId(), professor.getName(), professor.getDepartmentId());
        String query = queryBuilder.buildInsertQuery(columns, parameters);
        return jdbcTemplate.update(query);
    }

    @Override
    public Professor findProfessorById(int id) {
        try {
            String query = queryBuilder.buildFindByIdQuery(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Professor.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteProfessorById(int id) {
        String query = queryBuilder.buildDeleteByIdQuery(id);
        return jdbcTemplate.update(query);
    }

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
