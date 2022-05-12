package com.philips.informationservice.service;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.repository.JdbcInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class InformationServiceImpl implements InformationService{

    @Autowired
    private JdbcInformationRepository repository;

    @Override
    public void createCourse(Course course) {
        try {
            int result = repository.createCourse(course);
            if(result == 0)
                log.info("Course successfully added.");
            else
                throw new Exception();
        } catch (Exception e) {
            log.error("Course could not be added");
        }
    }
}
