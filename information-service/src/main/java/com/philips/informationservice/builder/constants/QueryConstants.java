package com.philips.informationservice.builder.constants;

public class QueryConstants {

    public static String SELECT = "SELECT * FROM TABLE_NAME WHERE id=";

    public static String INSERT = "INSERT INTO TABLE_NAME";

    public static String DELETE = "DELETE FROM TABLE_NAME WHERE id=";

    public static String SELECT_ALL_PROFESSORS = "SELECT p.name, array_agg(c.name) as courses " +
            "FROM professor p " +
            "INNER JOIN schedule s ON s.professor_id=p.id " +
            "INNER JOIN course c ON c.id=s.course_id GROUP BY p.name";

}
