package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;
import org.springframework.stereotype.Component;

/**
 * Query Builder class for Professor. It extends Abstract Query Builder
 */
@Component
public class ProfessorQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "PROFESSOR";

    /**
     * Returns table name
     * @return TABLE_NAME
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Builder method for returning all professors with their courses
     * @return query
     */
    public String buildFindAllProfessors() {
        return QueryConstants.SELECT_ALL_PROFESSORS;
    }
}
