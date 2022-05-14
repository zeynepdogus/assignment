package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course {
    private int id;
    private String name;
    private int departmentId;
    private int credits;
}
