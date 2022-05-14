package com.philips.informationservice.service;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.repository.department.JDBCDepartmentRepository;
import com.philips.informationservice.service.department.DepartmentServiceImpl;
import com.philips.informationservice.service.department.exception.DepartmentExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {
    @Mock
    private JDBCDepartmentRepository repository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @Before
    public void setUp() {
        department = new Department();
        department.setName("Test");
        department.setId(1);
    }

    @Test
    public void whenSaveDepartment_shouldReturnUser() {
        when(repository.findDepartmentById(department.getId())).thenReturn(null);
        when(repository.createDepartment(department)).thenReturn(1);

        departmentService.createDepartment(department);
        verify(repository).createDepartment(department);
    }

    @Test(expected = DepartmentExceptionHandler.DepartmentAlreadyExistsException.class)
    public void whenSaveDepartment_shouldReturnUserExists() {
        when(repository.findDepartmentById(department.getId())).thenReturn(department);
        when(repository.createDepartment(department)).thenReturn(0);

        departmentService.createDepartment(department);
    }

    @Test(expected = DepartmentExceptionHandler.DepartmentCreateException.class)
    public void whenSaveDepartment_shouldReturnInternalError() {
        when(repository.findDepartmentById(department.getId())).thenReturn(null);
        when(repository.createDepartment(department)).thenReturn(0);

        departmentService.createDepartment(department);
        verify(repository).createDepartment(department);
    }

    @Test
    public void whenGetDepartment_shouldReturnDepartmentWithId() {
        when(repository.findDepartmentById(department.getId())).thenReturn(department);

        Department departmentReturned = departmentService.getDepartment(this.department.getId());

        Assert.assertEquals(departmentReturned, department);
        verify(repository).findDepartmentById(department.getId());
    }

    @Test(expected = DepartmentExceptionHandler.DepartmentNotFoundException.class)
    public void whenGetDepartment_shouldReturnDepartmentNotFound() {
        when(repository.findDepartmentById(department.getId())).thenReturn(null);

        departmentService.getDepartment(this.department.getId());
    }

    @Test
    public void whenSaveDepartment_shouldDeleteUser() {
        when(repository.findDepartmentById(department.getId())).thenReturn(department);
        when(repository.deleteDepartmentById(department.getId())).thenReturn(1);

        departmentService.deleteDepartment(department.getId());
        verify(repository).deleteDepartmentById(department.getId());
    }

    @Test(expected = DepartmentExceptionHandler.DepartmentNotFoundException.class)
    public void whenSaveDepartment_shouldReturnDepartmentNotFound() {
        when(repository.findDepartmentById(department.getId())).thenReturn(null);
        when(repository.deleteDepartmentById(department.getId())).thenReturn(0);

        departmentService.deleteDepartment(department.getId());
        verify(repository).deleteDepartmentById(department.getId());
    }
}
