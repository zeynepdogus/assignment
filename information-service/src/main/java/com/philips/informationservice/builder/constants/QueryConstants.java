package com.philips.informationservice.builder.constants;

/**
 * Constants class for basic query expressions
 */
public class QueryConstants {

    /**
     * SELECT statement base for all queries
     */
    public static String SELECT = "SELECT * FROM TABLE_NAME WHERE id=";

    /**
     * INSERT statement base for all queries
     */
    public static String INSERT = "INSERT INTO TABLE_NAME";

    /**
     * DELETE statement base for all queries
     */
    public static String DELETE = "DELETE FROM TABLE_NAME WHERE id=";

    /**
     * SELECT statement for selecting all professors with their corresponding courses as a list
     */
    public static String SELECT_ALL_PROFESSORS = "SELECT p.name, array_agg(c.name) as courses " +
            "FROM professor p " +
            "INNER JOIN schedule s ON s.professor_id=p.id " +
            "INNER JOIN course c ON c.id=s.course_id GROUP BY p.name";

}
