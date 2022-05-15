package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Course object
 * @Data annotation is used from lombok for getters and setters
 */
@Data
@NoArgsConstructor
public class Course {
    private int id;
    private String name;
    private int departmentId;
    private int credits;
}
