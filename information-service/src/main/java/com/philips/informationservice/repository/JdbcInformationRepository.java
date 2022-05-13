package com.philips.informationservice.repository;

import com.philips.informationservice.builder.QueryBuilder;
import com.philips.informationservice.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcInformationRepository implements InformationRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QueryBuilder queryBuilder;

    @Override
    public int createCourse(Course course) {
        String query = queryBuilder.buildCreateQueryForCourse(course.getId(), course.getName(),
                course.getDepartment_id(), course.getCredits());
        return jdbcTemplate.update(query);
    }


    @Override
    public Course findCourseById(int id) {
        try {
            String query = queryBuilder.buildFindCourseById(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Course.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int deleteCourseById(int id) {
        String query = queryBuilder.buildDeleteCourseById(id);
        return jdbcTemplate.update(query);
    }

    @Override
    public int createDepartment(Department department) {
        String query = queryBuilder.buildCreateQueryForDepartment(department.getId(), department.getName());
        return jdbcTemplate.update(query);
    }


    @Override
    public Department findDepartmentById(int id) {
        try {
            String query = queryBuilder.buildFindDepartmentById(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Department.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteDepartmentById(int id) {
        String query = queryBuilder.buildDeleteDepartmentById(id);
        return jdbcTemplate.update(query);    }

    @Override
    public int createProfessor(Professor professor) {
        String query = queryBuilder.buildCreateQueryForProfessor(professor.getId(), professor.getName(),
                professor.getDepartment_id());
        return jdbcTemplate.update(query);
    }


    @Override
    public Professor findProfessorById(int id) {
        try {
            String query = queryBuilder.buildFindProfessorById(id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Professor.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteProfessorById(int id) {
        String query = queryBuilder.buildDeleteProfessorById(id);
        return jdbcTemplate.update(query);
    }

    @Override
    public int createSchedule(Schedule schedule) {
        String query = queryBuilder.buildCreateQueryForSchedule(schedule.getProfessor_id(), schedule.getCourse_id(),
                schedule.getSemester(), schedule.getYear());
        return jdbcTemplate.update(query);
    }

    @Override
    public Schedule findSchedule(int professor_id, int course_id) {
        try {
            String query = queryBuilder.buildFindSchedule(professor_id, course_id);
            return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Schedule.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteSchedule(int professor_id, int course_id) {
        String query = queryBuilder.buildDeleteSchedule(professor_id, course_id);
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
