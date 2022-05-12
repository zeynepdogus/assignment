package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Schedule {

    private int professor_id;
    private int course_id;
    private int semester;
    private int year;
}
