package com.philips.informationservice.repository.department;

import com.philips.informationservice.model.Department;

/**
 * Repository Interface for method declarations of Department
 */
public interface DepartmentRepository {

    int createDepartment(Department department);
    Department findDepartmentById(int id);
    int deleteDepartmentById(int id);
}
