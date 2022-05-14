package com.philips.informationservice.service.professor;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import com.philips.informationservice.repository.professor.JDBCProfessorRepository;
import com.philips.informationservice.service.professor.exception.ProfessorExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProfessorServiceImpl implements ProfessorService {

    private final JDBCProfessorRepository repository;

    @Autowired
    public ProfessorServiceImpl(JDBCProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createProfessor(Professor professor) {
        Optional<Professor> professorById = Optional.ofNullable(repository.findProfessorById(professor.getId()));
        if (professorById.isPresent()) {
            throw new ProfessorExceptionHandler.ProfessorAlreadyExistsException();
        }
        int professorToBeAdded = repository.createProfessor(professor);
        if (professorToBeAdded == 0)
            throw new ProfessorExceptionHandler.ProfessorCreateException();
    }

    @Override
    public Professor getProfessor(int id) {
        Optional<Professor> professorOptional = Optional.ofNullable(repository.findProfessorById(id));
        return professorOptional.orElseThrow(ProfessorExceptionHandler.ProfessorNotFoundException::new);
    }

    @Override
    public void deleteProfessor(int id) {
        Optional<Professor> professorById = Optional.ofNullable(repository.findProfessorById(id));
        if (professorById.isEmpty()) {
            throw new ProfessorExceptionHandler.ProfessorNotFoundException();
        }
        Professor professor = professorById.get();
        repository.deleteProfessorById(professor.getId());
    }

    @Override
    public List<ProfessorDetails> findAllProfessors() {
        Optional<List<ProfessorDetails>> professorDetailsOptional = Optional.ofNullable(repository.findAllProfessors());
        return professorDetailsOptional.orElseThrow(ProfessorExceptionHandler.ProfessorNotFoundException::new);
    }
}
