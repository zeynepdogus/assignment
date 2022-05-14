package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Professor {

    private int id;
    private String name;
    private int departmentId;
}
