package com.philips.informationservice.builder;

import org.springframework.stereotype.Component;

/**
 * Query Builder class for Course. It extends Abstract Query Builder
 */
@Component
public class CourseQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "COURSE";

    /**
     * Returns table name
     * @return TABLE_NAME
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}
