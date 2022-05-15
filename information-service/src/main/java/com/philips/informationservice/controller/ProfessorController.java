package com.philips.informationservice.controller;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import com.philips.informationservice.service.professor.ProfessorService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Professors
 */
@RestController
@RequestMapping("/api/v1/professor-management")
@RequiredArgsConstructor
public class ProfessorController {

    @Autowired
    public ProfessorService professorService;

    /**
     * Creates a rest endpoint to create a new department
     * Api responses are set for Swagger documentation
     * @param professor
     * @return
     */
    @PostMapping("/professors")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "Professor already exists."),
            @ApiResponse(code = 500, message = "Professor could not be created."),
            @ApiResponse(code = 404, message = "Professor not found.")})
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        professorService.createProfessor(professor);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    /**
     * Creates a rest endpoint to get a professor with professor id.
     * Api responses are set for Swagger documentation
     * @param id
     * @return
     */
    @GetMapping("/professors/{id}")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Professor not found.")})
    public ResponseEntity<Professor> getProfessor(@PathVariable("id") int id) {
        Professor course = professorService.getProfessor(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * Creates a rest endpoint to delete a professor if it exists
     * Api responses are set for Swagger documentation
     * @param id
     * @return
     */
    @DeleteMapping("/professors/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Nothing to delete."),
            @ApiResponse(code = 404, message = "Professor not found.")})
    public ResponseEntity<HttpStatus> deleteProfessor(@PathVariable("id") int id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a rest endpoint to get all professors with their courses
     * Api responses are set for Swagger documentation
     * @return
     */
    @GetMapping("/professors")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Professors not found.")})
    public ResponseEntity<List<ProfessorDetails>> getAllProfessors() {
        List<ProfessorDetails> allProfessors = professorService.findAllProfessors();
        return new ResponseEntity<>(allProfessors, HttpStatus.OK);
    }
}
