package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;

import java.util.List;

public abstract class AbstractQueryBuilder implements QueryBuilder {

    public abstract String getTableName();


    @Override
    public String buildFindByIdQuery(int id) {
        return QueryConstants.SELECT.replace("TABLE_NAME", getTableName()) + id;
    }

    @Override
    public String buildInsertQuery(List<String> columns, List<Object> parameters) {
        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder parameterBuilder = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String string : columns) {
            columnBuilder.append(string).append(",");
        }
        columnBuilder.deleteCharAt(columnBuilder.length() - 1);

        for (Object object : parameters) {
            if (object instanceof String) {
                parameterBuilder.append("'").append(object).append("'").append(",");
            } else {
                parameterBuilder.append(object).append(",");
            }
        }
        parameterBuilder.deleteCharAt(parameterBuilder.length() - 1);

        query.append(QueryConstants.INSERT.replace("TABLE_NAME", getTableName())
                + " ( " + columnBuilder.toString() + " ) " + " VALUES " + "( " + parameterBuilder.toString() + " )");
        return query.toString();
    }

    @Override
    public String buildDeleteByIdQuery(int id) {
        return QueryConstants.DELETE.replace("TABLE_NAME", getTableName()) + id;
    }

}
