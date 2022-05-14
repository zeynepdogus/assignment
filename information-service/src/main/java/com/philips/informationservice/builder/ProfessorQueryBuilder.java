package com.philips.informationservice.builder;

import com.philips.informationservice.builder.constants.QueryConstants;
import org.springframework.stereotype.Component;

@Component
public class ProfessorQueryBuilder extends AbstractQueryBuilder {
    public static String TABLE_NAME = "PROFESSOR";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public String buildFindAllProfessors() {
        return QueryConstants.SELECT_ALL_PROFESSORS;
    }
}
