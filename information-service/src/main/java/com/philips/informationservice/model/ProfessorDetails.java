package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorDetails {
    private String name;
    private String[] courses;
}
