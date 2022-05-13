package com.philips.informationservice.controller;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    @Autowired
    public ProfessorService professorService;

    @PostMapping("/create")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor course) {
        professorService.createProfessor(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable("id") int id) {
        Professor course = professorService.getProfessor(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProfessor(@PathVariable("id") int id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
