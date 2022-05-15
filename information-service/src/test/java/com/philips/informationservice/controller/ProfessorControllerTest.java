package com.philips.informationservice.controller;

import com.philips.informationservice.model.Professor;
import com.philips.informationservice.model.ProfessorDetails;
import com.philips.informationservice.repository.professor.JDBCProfessorRepository;
import com.philips.informationservice.service.professor.ProfessorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfessorServiceImpl professorService;

    @MockBean
    private JDBCProfessorRepository repository;

    @Autowired
    private JacksonTester<Professor> json;

    @Autowired
    private JacksonTester<List<ProfessorDetails>> jsonList;

    private Professor professor;

    @Before
    public void setUp() {
        professor = new Professor();
        professor.setName("Test");
        professor.setId(1);
        professor.setDepartmentId(2);
    }

    @Test
    public void whenCreateProfessor() throws Exception {
        Mockito.when(repository.findProfessorById(professor.getId())).thenReturn(null);
        Mockito.when(repository.createProfessor(professor)).thenReturn(1);
        professorService.createProfessor(professor);
        mvc.perform(
                post(new URI("/api/v1/professor-management/professors"))
                        .content(json.write(professor).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetProfessor() throws Exception {
        Mockito.when(repository.findProfessorById(professor.getId())).thenReturn(professor);

        professorService.getProfessor(this.professor.getId());

        mvc.perform(get(new URI("/api/v1/professor-management/professors/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(professor).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetAllProfessors() throws Exception {
        List<ProfessorDetails> professorDetails = new ArrayList<>();
        ProfessorDetails details = new ProfessorDetails();
        details.setName(professor.getName());
        details.setCourses(new String[]{"course1", "course2"});

        professorDetails.add(details);

        Mockito.when(repository.findAllProfessors()).thenReturn(professorDetails);

        professorService.findAllProfessors();

        mvc.perform(get(new URI("/api/v1/professor-management/professors"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonList.write(professorDetails).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteProfessor() throws Exception {
        Mockito.when(repository.findProfessorById(professor.getId())).thenReturn(professor);
        Mockito.when(repository.deleteProfessorById(professor.getId())).thenReturn(1);

        professorService.deleteProfessor(professor.getId());
        mvc.perform(delete(new URI("/api/v1/professor-management/professors/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(professor).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
