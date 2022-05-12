package com.philips.informationservice.repository;

import com.philips.informationservice.model.Course;
import com.philips.informationservice.model.Department;
import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.Schedule;

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
}
