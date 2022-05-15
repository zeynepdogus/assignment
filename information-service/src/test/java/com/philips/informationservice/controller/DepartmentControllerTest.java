package com.philips.informationservice.controller;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.repository.department.JDBCDepartmentRepository;
import com.philips.informationservice.service.department.DepartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentServiceImpl departmentService;

    @MockBean
    private JDBCDepartmentRepository repository;

    @Autowired
    private JacksonTester<Department> json;

    private Department department;

    @Before
    public void setUp() {
        department = new Department();
        department.setName("Test");
        department.setId(1);
    }

    @Test
    public void whenCreateDepartment() throws Exception {
        Mockito.when(repository.findDepartmentById(department.getId())).thenReturn(null);
        Mockito.when(repository.createDepartment(department)).thenReturn(1);
        departmentService.createDepartment(department);
        mvc.perform(
                post(new URI("/api/v1/department-management/departments"))
                        .content(json.write(department).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetDepartment() throws Exception {
        Mockito.when(repository.findDepartmentById(department.getId())).thenReturn(department);

        departmentService.getDepartment(this.department.getId());

        mvc.perform(get(new URI("/api/v1/department-management/departments/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(department).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteDepartment() throws Exception {
        Mockito.when(repository.findDepartmentById(department.getId())).thenReturn(department);
        Mockito.when(repository.deleteDepartmentById(department.getId())).thenReturn(1);

        departmentService.deleteDepartment(department.getId());
        mvc.perform(delete(new URI("/api/v1/department-management/departments/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(department).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
