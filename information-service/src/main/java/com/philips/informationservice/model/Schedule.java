package com.philips.informationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Schedule {

    private int professorId;
    private int courseId;
    private int semester;
    private int year;
}
