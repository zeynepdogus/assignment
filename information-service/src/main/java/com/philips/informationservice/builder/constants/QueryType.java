package com.philips.informationservice.builder.constants;

import lombok.Builder;
import lombok.Getter;

@Builder
public class QueryType {

    @Getter
    Type queryType;

    public enum Type {
        SQL,
        NATIVE,
        UNDEFINED
    }


}
