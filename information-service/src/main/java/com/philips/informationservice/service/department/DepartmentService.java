package com.philips.informationservice.service.department;

import com.philips.informationservice.model.Department;

public interface DepartmentService {
    public void createDepartment(Department department);
    public Department getDepartment(int id);
    public void deleteDepartment(int id);
}
