package com.philips.informationservice.service.department;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.repository.department.JDBCDepartmentRepository;
import com.philips.informationservice.service.department.exception.DepartmentExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {

    private final JDBCDepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(JDBCDepartmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Service method to create a department object
     * It @throws CourseAlreadyExistsException or CourseCreateException if the service fails
     * @param department
     */
    @Override
    public void createDepartment(Department department) {
        Optional<Department> departmentById = Optional.ofNullable(repository.findDepartmentById(department.getId()));
        if (departmentById.isPresent()) {
            throw new DepartmentExceptionHandler.DepartmentAlreadyExistsException();
        }
        int departmentToBeAdded = repository.createDepartment(department);
        if (departmentToBeAdded == 0)
            throw new DepartmentExceptionHandler.DepartmentCreateException();

    }

    /**
     * Service method to find the department by id
     * It @throws DepartmentNotFoundException if the service fails
     * @param id
     * @return
     */
    @Override
    public Department getDepartment(int id) {
        Optional<Department> departmentOptional = Optional.ofNullable(repository.findDepartmentById(id));
        return departmentOptional.orElseThrow(DepartmentExceptionHandler.DepartmentNotFoundException::new);
    }

    /**
     * Service method to department by id
     * It @throws DepartmentNotFoundException if the service fails
     * @param id
     */
    @Override
    public void deleteDepartment(int id) {
        Optional<Department> departmentById = Optional.ofNullable(repository.findDepartmentById(id));
        if (departmentById.isEmpty()) {
            throw new DepartmentExceptionHandler.DepartmentNotFoundException();
        }
        Department department = departmentById.get();
        repository.deleteDepartmentById(department.getId());
    }
}
