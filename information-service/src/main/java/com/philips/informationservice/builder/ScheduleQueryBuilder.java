package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;
import org.springframework.stereotype.Component;

/**
 * Query Builder class for Schedule. It extends Abstract Query Builder
 */
@Component
public class ScheduleQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "SCHEDULE";

    /**
     * Returns table name
     * @return TABLE_NAME
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Builds select query for finding a schedule.
     * Since it gets different parameters to find from other tables,
     * method overloading is used.
     * @param professor_id
     * @param course_id
     * @return
     */
    public String buildFindByIdQuery(int professor_id, int course_id) {
        return QueryConstants.SELECT.replace("TABLE_NAME", getTableName())
                .replace("id=", "professor_id= ") + professor_id
                + "AND course_id= " + course_id;
    }

    /**
     * Builds select query for finding a schedule.
     * Since it gets different parameters to delete from other tables,
     * method overloading is used.
     * @param professor_id
     * @param course_id
     * @return
     */
    public String buildDeleteByIdQuery(int professor_id, int course_id) {
        return QueryConstants.DELETE.replace("TABLE_NAME", getTableName())
                .replace("id=", "professor_id= ") + professor_id
                + "AND course_id= " + course_id;
    }
}
