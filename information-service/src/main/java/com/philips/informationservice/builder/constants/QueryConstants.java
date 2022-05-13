package com.philips.informationservice.builder.constants;

public class QueryConstants {

    public static String INSERT_INTO_COURSE = "INSERT INTO course (id, name, department_id, credits) values ";

    public static String INSERT_INTO_PROFESSOR = "INSERT INTO professor (id, name, department_id) values ";

    public static String INSERT_INTO_DEPARTMENT = "INSERT INTO department (id, name) values ";

    public static String INSERT_INTO_SCHEDULE = "INSERT INTO schedule (professor_id, course_id, semester, year) values";

    public static String SELECT_COURSE = "SELECT * FROM course WHERE id=";

    public static String SELECT_PROFESSOR = "SELECT * FROM professor WHERE id=";

    public static String SELECT_DEPARTMENT = "SELECT * FROM department WHERE id=";

    public static String SELECT_SCHEDULE = "SELECT * FROM schedule WHERE professor_id = ? and course_id = ?";

    public static String DELETE_COURSE = "DELETE FROM course WHERE id=";

    public static String DELETE_PROFESSOR = "DELETE FROM professor WHERE id=";

    public static String DELETE_DEPARTMENT = "DELETE FROM department WHERE id=";

    public static String DELETE_SCHEDULE = "DELETE FROM schedule WHERE professor_id = ? and course_id = ?";

    public static String SELECT_PROFESSOR_WITH_COURSES = "SELECT p.name, array_agg(c.name) as courses " +
            "FROM professor p " +
            "INNER JOIN schedule s ON s.professor_id=p.id " +
            "INNER JOIN course c ON c.id=s.course_id GROUP BY p.name";
}
