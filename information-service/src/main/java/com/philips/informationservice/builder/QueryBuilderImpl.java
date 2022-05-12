package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;
import org.springframework.stereotype.Service;

@Service
public class QueryBuilderImpl implements QueryBuilder{

    @Override
    public String buildCreateQueryForCourse(int id, String name, int department_id, int credits) {
        return QueryConstants.INSERT_INTO_COURSE
                + "(" + id + name + department_id + credits + ")";
    }


    @Override
    public String buildFindCourseById(int id) {
        return QueryConstants.SELECT_COURSE + id;
    }

    @Override
    public String buildDeleteCourseById(int id) {
        return QueryConstants.DELETE_COURSE + id;
    }

    @Override
    public String buildCreateQueryForDepartment(int id, String name) {
        return QueryConstants.INSERT_INTO_DEPARTMENT
                + "(" + id + name + ")";
    }

    @Override
    public String buildFindDepartmentById(int id) {
        return QueryConstants.SELECT_DEPARTMENT + id;
    }

    @Override
    public String buildDeleteDepartmentById(int id) {
        return QueryConstants.DELETE_DEPARTMENT + id;
    }

    @Override
    public String buildCreateQueryForProfessor(int id, String name, int department_id) {
        return QueryConstants.INSERT_INTO_PROFESSOR
                + "(" + id + name + department_id + ")";
    }

    @Override
    public String buildFindProfessorById(int id) {
        return QueryConstants.SELECT_PROFESSOR + id;
    }

    @Override
    public String buildDeleteProfessorById(int id) {
        return QueryConstants.DELETE_PROFESSOR + id;
    }

    @Override
    public String buildCreateQueryForSchedule(int professor_id, int course_id, int semester, int year) {
        return QueryConstants.INSERT_INTO_SCHEDULE
                + "(" + professor_id + course_id + semester + year + ")";
    }

    @Override
    public String buildFindSchedule(int professor_id, int course_id) {
        return QueryConstants.SELECT_SCHEDULE
                .replace("?", String.valueOf(professor_id))
                .replace("?", String.valueOf(course_id));
    }

    @Override
    public String buildDeleteSchedule(int professor_id, int course_id) {
        return QueryConstants.DELETE_SCHEDULE
                .replace("?", String.valueOf(professor_id))
                .replace("?", String.valueOf(course_id));
    }
}
