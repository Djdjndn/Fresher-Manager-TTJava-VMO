package com.example.fresher_manager.controller;

import com.example.fresher_manager.config.TestConfig;
import com.example.fresher_manager.dto.request.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.service.CenterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CenterController.class)
@Import(TestConfig.class)
class CenterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CenterService centerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCenter_WithValidData_ShouldReturnCreated() throws Exception {
        // Arrange
        CenterRequest request = new CenterRequest("Center A", null);
        Center center = Center.builder()
                .id(1L)
                .name("Center A")
                .build();

        when(centerService.createCenter(any(CenterRequest.class))).thenReturn(center);

        // Act & Assert
        mockMvc.perform(post("/api/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Center created successfully"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Center A"));
    }

    @Test
    void getAllCenters_ShouldReturnCenterList() throws Exception {
        // Arrange
        Center center1 = Center.builder().id(1L).name("Center A").build();
        Center center2 = Center.builder().id(2L).name("Center B").build();
        List<Center> centers = List.of(center1, center2);

        when(centerService.getAllCenters()).thenReturn(centers);

        // Act & Assert
        mockMvc.perform(get("/api/centers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("Center A"))
                .andExpect(jsonPath("$.data[1].name").value("Center B"));
    }

    @Test
    void getCenterById_WhenExists_ShouldReturnCenter() throws Exception {
        // Arrange
        Center center = Center.builder()
                .id(1L)
                .name("Center A")
                .build();

        when(centerService.getCenterById(1L)).thenReturn(center);

        // Act & Assert
        mockMvc.perform(get("/api/centers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Center A"));
    }
}