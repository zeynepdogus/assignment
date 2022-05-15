package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Schedule object
 * @Data annotation is used from lombok for getters and setters
 */
@Data
@NoArgsConstructor
public class Schedule {

    private int professorId;
    private int courseId;
    private int semester;
    private int year;
}
