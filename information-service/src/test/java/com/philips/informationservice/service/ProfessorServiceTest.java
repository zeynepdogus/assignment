package com.philips.informationservice.service;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import com.philips.informationservice.repository.professor.JDBCProfessorRepository;
import com.philips.informationservice.service.professor.ProfessorServiceImpl;
import com.philips.informationservice.service.professor.exception.ProfessorExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessorServiceTest {
    @Mock
    private JDBCProfessorRepository repository;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    private Professor professor;

    @Before
    public void setUp() {
        professor = new Professor();
        professor.setName("Test");
        professor.setId(1);
        professor.setDepartmentId(2);
    }

    @Test
    public void whenSaveProfessor_shouldReturnProfessor() {
        when(repository.findProfessorById(professor.getId())).thenReturn(null);
        when(repository.createProfessor(professor)).thenReturn(1);

        professorService.createProfessor(professor);
        verify(repository).createProfessor(professor);
    }

    @Test(expected = ProfessorExceptionHandler.ProfessorAlreadyExistsException.class)
    public void whenSaveProfessor_shouldReturnProfessorExists() {
        when(repository.findProfessorById(professor.getId())).thenReturn(professor);
        when(repository.createProfessor(professor)).thenReturn(0);

        professorService.createProfessor(professor);
    }

    @Test(expected = ProfessorExceptionHandler.ProfessorCreateException.class)
    public void whenSaveProfessor_shouldReturnInternalError() {
        when(repository.findProfessorById(professor.getId())).thenReturn(null);
        when(repository.createProfessor(professor)).thenReturn(0);

        professorService.createProfessor(professor);
        verify(repository).createProfessor(professor);
    }

    @Test
    public void whenGetProfessor_shouldReturnProfessorWithId() {
        when(repository.findProfessorById(professor.getId())).thenReturn(professor);

        Professor professorReturned = professorService.getProfessor(this.professor.getId());

        Assert.assertEquals(professorReturned, professor);
        verify(repository).findProfessorById(professor.getId());
    }

    @Test(expected = ProfessorExceptionHandler.ProfessorNotFoundException.class)
    public void whenGetProfessor_shouldReturnProfessorNotFound() {
        when(repository.findProfessorById(professor.getId())).thenReturn(null);

        professorService.getProfessor(this.professor.getId());
    }

    @Test
    public void whenDeleteProfessor_shouldDeleteProfessor() {
        when(repository.findProfessorById(professor.getId())).thenReturn(professor);
        when(repository.deleteProfessorById(professor.getId())).thenReturn(1);

        professorService.deleteProfessor(professor.getId());
        verify(repository).deleteProfessorById(professor.getId());
    }

    @Test(expected = ProfessorExceptionHandler.ProfessorNotFoundException.class)
    public void whenDeleteProfessor_shouldReturnProfessorNotFound() {
        when(repository.findProfessorById(professor.getId())).thenReturn(null);
        when(repository.deleteProfessorById(professor.getId())).thenReturn(0);

        professorService.deleteProfessor(professor.getId());
        verify(repository).deleteProfessorById(professor.getId());
    }

    @Test
    public void whenGetProfessor_shouldReturnAllProfessors() {
        List<ProfessorDetails> professorDetails = new ArrayList<>();
        ProfessorDetails details = new ProfessorDetails();
        details.setName(professor.getName());
        details.setCourses(new String[]{"course1", "course2"});

        professorDetails.add(details);

        when(repository.findAllProfessors()).thenReturn(professorDetails);

        List<ProfessorDetails> professorsReturned = professorService.findAllProfessors();

        Assert.assertEquals(professorsReturned, professorDetails);
        verify(repository).findAllProfessors();
    }
}
