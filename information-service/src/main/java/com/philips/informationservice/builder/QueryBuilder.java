package com.philips.informationservice.builder;

import java.util.List;

public interface QueryBuilder {

    String buildFindByIdQuery(int id);

    String buildInsertQuery(List<String> columns, List<Object> parameters);

    String buildDeleteByIdQuery(int id);
}
