package com.philips.informationservice.repository;

import com.philips.informationservice.builder.QueryBuilder;
import com.philips.informationservice.model.Course;
import com.philips.informationservice.model.Department;
import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        String query = queryBuilder.buildFindCourseById(id);
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Course.class));
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
        String query = queryBuilder.buildFindDepartmentById(id);
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Department.class));
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
        String query = queryBuilder.buildFindProfessorById(id);
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Professor.class));
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
        String query = queryBuilder.buildFindSchedule(professor_id, course_id);
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Schedule.class));
    }

    @Override
    public int deleteSchedule(int professor_id, int course_id) {
        String query = queryBuilder.buildDeleteSchedule(professor_id, course_id);
        return jdbcTemplate.update(query);
    }
}
