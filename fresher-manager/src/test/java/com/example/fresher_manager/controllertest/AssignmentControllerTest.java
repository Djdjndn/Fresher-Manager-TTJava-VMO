package com.example.fresher_manager.controllertest;


import com.example.fresher_manager.controller.AssignmentController;
import com.example.fresher_manager.entity.Assignment;
import com.example.fresher_manager.service.AssignmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AssignmentController.class)
class AssignmentControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean AssignmentService assignmentService;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void assignFresherToProject_ok() throws Exception {
        Assignment a = new Assignment();
        a.setId(1L);
        Mockito.when(assignmentService.assignFresherToProject(1L, 2L)).thenReturn(a);

        mockMvc.perform(post("/api/assignments/assign")
                .with(httpBasic("admin","admin123"))
                .param("fresherId","1")
                .param("projectId","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getAssignmentsByFresher_ok() throws Exception {
        Assignment a = new Assignment(); a.setId(5L);
        Mockito.when(assignmentService.getAssignmentsByFresher(1L)).thenReturn(List.of(a));

        mockMvc.perform(get("/api/assignments/by-fresher/1")
                .with(httpBasic("admin","admin123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(5));
    }
}