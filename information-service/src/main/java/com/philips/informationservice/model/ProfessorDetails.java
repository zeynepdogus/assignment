package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for ProfessorDetails object.
 * This is the return type of finding all professors method
 * @Data annotation is used from lombok for getters and setters
 */
@Data
@NoArgsConstructor
public class ProfessorDetails {
    private String name;
    private String[] courses;
}
