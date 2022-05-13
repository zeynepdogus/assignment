package com.philips.informationservice.repository;

import com.philips.informationservice.model.*;
import netscape.javascript.JSObject;

import java.util.List;

public interface InformationRepository {
    int createCourse(Course course);
    Course findCourseById(int id);
    int deleteCourseById(int id);

    int createDepartment(Department department);
    Department findDepartmentById(int id);
    int deleteDepartmentById(int id);

    int createProfessor(Professor professor);
    Professor findProfessorById(int id);
    int deleteProfessorById(int id);

    int createSchedule(Schedule schedule);
    Schedule findSchedule(int professor_id, int course_id);
    int deleteSchedule(int professor_id, int course_id);

    List<ProfessorDetails> findAllProfessors();
}
