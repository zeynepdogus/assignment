package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Professor object
 * @Data annotation is used from lombok for getters and setters
 */
@Data
@NoArgsConstructor
public class Professor {

    private int id;
    private String name;
    private int departmentId;
}
