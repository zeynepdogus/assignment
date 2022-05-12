package com.philips.informationservice.builder;

public interface QueryBuilder {

    public String buildCreateQueryForCourse(int id, String name, int department_id, int credits);
    public String buildFindCourseById(int id);
    public String buildDeleteCourseById(int id);
    public String buildCreateQueryForDepartment(int id, String name);
    public String buildFindDepartmentById(int id);
    public String buildDeleteDepartmentById(int id);
    public String buildCreateQueryForProfessor(int id, String name, int department_id);
    public String buildFindProfessorById(int id);
    public String buildDeleteProfessorById(int id);
    public String buildCreateQueryForSchedule(int professor_id, int course_id, int semester, int year);
    public String buildFindSchedule(int professor_id, int course_id);
    public String buildDeleteSchedule(int professor_id, int course_id);
}
