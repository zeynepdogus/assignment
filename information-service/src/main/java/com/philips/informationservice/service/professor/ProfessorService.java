package com.philips.informationservice.service.professor;

import com.philips.informationservice.model.Professor;

public interface ProfessorService {
    public void createProfessor(Professor professor);
    public Professor getProfessor(int id);
    public void deleteProfessor(int id);
}
