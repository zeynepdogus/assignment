package com.philips.informationservice.service.professor;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;

import java.util.List;

public interface ProfessorService {
    public void createProfessor(Professor professor);
    public Professor getProfessor(int id);
    public void deleteProfessor(int id);
    public List<ProfessorDetails> findAllProfessors();
}
