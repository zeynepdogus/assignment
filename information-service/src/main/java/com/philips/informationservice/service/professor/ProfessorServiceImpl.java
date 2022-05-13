package com.philips.informationservice.service.professor;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.repository.JdbcInformationRepository;
import com.philips.informationservice.service.professor.exception.ProfessorAlreadyExistsException;
import com.philips.informationservice.service.professor.exception.ProfessorCreateException;
import com.philips.informationservice.service.professor.exception.ProfessorNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    private JdbcInformationRepository repository;

    @Override
    public void createProfessor(Professor professor) {
        Optional<Professor> professorById = Optional.ofNullable(repository.findProfessorById(professor.getId()));
        if (professorById.isPresent()) {
            throw new ProfessorAlreadyExistsException();
        }
        int professorToBeAdded = repository.createProfessor(professor);
        if(professorToBeAdded == 0)
            throw new ProfessorCreateException();
    }

    @Override
    public Professor getProfessor(int id) {
        Optional<Professor> professorOptional = Optional.ofNullable(repository.findProfessorById(id));
        return professorOptional.orElseThrow(ProfessorNotFoundException::new);
    }

    @Override
    public void deleteProfessor(int id) {
        Optional<Professor> professorById = Optional.ofNullable(repository.findProfessorById(id));
        if (professorById.isEmpty()) {
            throw new ProfessorNotFoundException();
        }
        Professor professor = professorById.get();
        repository.deleteProfessorById(professor.getId());
    }
}
