package com.philips.informationservice.model.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorDetails {
    private String name;
    private String[] courses;
}
