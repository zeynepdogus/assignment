package com.philips.informationservice.repository.professor;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;

import java.util.List;

/**
 * Repository Interface for method declarations of Professor
 */
public interface ProfessorRepository {

    int createProfessor(Professor professor);
    Professor findProfessorById(int id);
    int deleteProfessorById(int id);
    List<ProfessorDetails> findAllProfessors();
}
