package com.philips.informationservice.builder;

import org.springframework.stereotype.Component;

@Component
public class CourseQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "COURSE";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}
