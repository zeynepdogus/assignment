package com.philips.informationservice.controller;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.service.department.DepartmentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department-management")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @PostMapping("/departments")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "Department already exists."),
            @ApiResponse(code = 500, message = "Department could not be created."),
            @ApiResponse(code = 404, message = "Department not found.")})
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        departmentService.createDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Department not found.")})
    public ResponseEntity<Department> getDepartment(@PathVariable("id") int id) {
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Nothing to delete."),
            @ApiResponse(code = 404, message = "Department not found.")})
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
