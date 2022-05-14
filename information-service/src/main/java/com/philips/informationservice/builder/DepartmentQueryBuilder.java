package com.philips.informationservice.builder;

import org.springframework.stereotype.Component;

@Component
public class DepartmentQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "DEPARTMENT";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
