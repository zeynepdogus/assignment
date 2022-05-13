package com.philips.informationservice.controller;

import com.philips.informationservice.model.Department;
import com.philips.informationservice.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department course) {
        departmentService.createDepartment(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") int id) {
        Department course = departmentService.getDepartment(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
