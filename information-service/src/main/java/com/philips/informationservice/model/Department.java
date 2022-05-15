package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Department object
 * @Data annotation is used from lombok for getters and setters
 */
@Data
@NoArgsConstructor
public class Department {

    private int id;
    private String name;
}
