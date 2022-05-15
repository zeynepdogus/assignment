package com.philips.informationservice.builder;

import org.springframework.stereotype.Component;

/**
 * Query Builder class for Department. It extends Abstract Query Builder
 */
@Component
public class DepartmentQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "DEPARTMENT";

    /**
     * Returns table name
     * @return TABLE_NAME
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
