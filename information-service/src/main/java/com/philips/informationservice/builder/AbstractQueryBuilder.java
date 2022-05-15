package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;

import java.util.List;

/**
 * Abstract Query Builder class which implements Query Builder for build methods
 * It implements the methods from Query Builder
 */
public abstract class AbstractQueryBuilder implements QueryBuilder {

    /**
     * Gets table name for the table
     * @return table name
     */
    public abstract String getTableName();


    /**
     * Builds select query for tables
     * @param id
     * @return select query as String
     */
    @Override
    public String buildFindByIdQuery(int id) {
        return QueryConstants.SELECT.replace("TABLE_NAME", getTableName()) + id;
    }

    /**
     * Builds insert query for tables. It gets column names as a String list as well as
     * parameters as a list that the columns refer to.
     * @param columns
     * @param parameters
     * @return
     */
    @Override
    public String buildInsertQuery(List<String> columns, List<Object> parameters) {
        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder parameterBuilder = new StringBuilder();
        StringBuilder query = new StringBuilder();

        /**
         * Creates the string for columns as (?,?,?,?)
         */
        for (String string : columns) {
            columnBuilder.append(string).append(",");
        }
        columnBuilder.deleteCharAt(columnBuilder.length() - 1);

        /**
         * Creates the string for parameters as (?,?,?,?)
         * It adds şingle quotes for string parameters
         */
        for (Object object : parameters) {
            if (object instanceof String) {
                parameterBuilder.append("'").append(object).append("'").append(",");
            } else {
                parameterBuilder.append(object).append(",");
            }
        }
        /**
         * Deletes excess , at the end of the string
         */
        parameterBuilder.deleteCharAt(parameterBuilder.length() - 1);

        query.append(QueryConstants.INSERT.replace("TABLE_NAME", getTableName())
                + " ( " + columnBuilder.toString() + " ) " + " VALUES " + "( " + parameterBuilder.toString() + " )");
        return query.toString();
    }

    /**
     * Builds delete query for tables
     * @param id
     * @return
     */
    @Override
    public String buildDeleteByIdQuery(int id) {
        return QueryConstants.DELETE.replace("TABLE_NAME", getTableName()) + id;
    }

}
