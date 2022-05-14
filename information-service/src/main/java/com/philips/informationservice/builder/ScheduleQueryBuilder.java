package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;
import org.springframework.stereotype.Component;

@Component
public class ScheduleQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "SCHEDULE";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public String buildFindByIdQuery(int professor_id, int course_id) {
        return QueryConstants.SELECT.replace("TABLE_NAME", getTableName())
                .replace("id=", "professor_id= ") + professor_id
                + "AND course_id= " + course_id;
    }

    public String buildDeleteByIdQuery(int professor_id, int course_id) {
        return QueryConstants.DELETE.replace("TABLE_NAME", getTableName())
                .replace("id=", "professor_id= ") + professor_id
                + "AND course_id= " + course_id;
    }
}
