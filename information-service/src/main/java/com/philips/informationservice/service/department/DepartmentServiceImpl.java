package com.philips.informationservice.service.department;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.repository.JdbcInformationRepository;
import com.philips.informationservice.service.department.exception.DepartmentAlreadyExistsException;
import com.philips.informationservice.service.department.exception.DepartmentCreateException;
import com.philips.informationservice.service.department.exception.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private JdbcInformationRepository repository;

    @Override
    public void createDepartment(Department department) {
        Optional<Department> departmentById = Optional.ofNullable(repository.findDepartmentById(department.getId()));
        if (departmentById.isPresent()) {
            throw new DepartmentAlreadyExistsException();
        }
        int departmentToBeAdded = repository.createDepartment(department);
        if(departmentToBeAdded == 0)
            throw new DepartmentCreateException();

    }

    @Override
    public Department getDepartment(int id) {
        Optional<Department> departmentOptional = Optional.ofNullable(repository.findDepartmentById(id));
        return departmentOptional.orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public void deleteDepartment(int id) {
        Optional<Department> departmentById = Optional.ofNullable(repository.findDepartmentById(id));
        if (departmentById.isEmpty()) {
            throw new DepartmentNotFoundException();
        }
        Department department = departmentById.get();
        repository.deleteDepartmentById(department.getId());
    }
}
