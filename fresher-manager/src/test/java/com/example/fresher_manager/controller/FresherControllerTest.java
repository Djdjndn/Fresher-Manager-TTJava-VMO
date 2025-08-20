// FresherControllerTest.java
package com.example.fresher_manager.controller;

import com.example.fresher_manager.config.TestConfig;
import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.service.FresherService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FresherController.class)
@Import(TestConfig.class)
class FresherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FresherService fresherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFresher_WithValidData_ShouldReturnCreated() throws Exception {
        // Arrange
        FresherRequest request = new FresherRequest("John Doe", "john@email.com", "Java", 8.5);
        Fresher fresher = Fresher.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .programingLanguage("Java")
                .score(8.5)
                .build();

        when(fresherService.createFresher(any(FresherRequest.class))).thenReturn(fresher);

        // Act & Assert
        mockMvc.perform(post("/api/freshers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Fresher created successfully"))
                .andExpect(jsonPath("$.data.name").value("John Doe"));
    }

    @Test
    void getFresherById_WhenExists_ShouldReturnFresher() throws Exception {
        // Arrange
        Fresher fresher = Fresher.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .build();

        when(fresherService.getFresherById(1L)).thenReturn(fresher);

        // Act & Assert
        mockMvc.perform(get("/api/freshers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("John Doe"));
    }

    @Test
    void getAllFreshers_ShouldReturnFresherList() throws Exception {
        // Arrange
        Fresher fresher1 = Fresher.builder().id(1L).name("John Doe").email("john@email.com").build();
        Fresher fresher2 = Fresher.builder().id(2L).name("Jane Smith").email("jane@email.com").build();
        List<Fresher> freshers = List.of(fresher1, fresher2);

        when(fresherService.getAllFreshers()).thenReturn(freshers);

        // Act & Assert
        mockMvc.perform(get("/api/freshers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Get all Freshers"))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[1].name").value("Jane Smith"));
    }

    @Test
    void searchByName_ShouldReturnMatchingFreshers() throws Exception {
        // Arrange
        Fresher fresher1 = Fresher.builder().id(1L).name("John Doe").email("john1@email.com").build();
        Fresher fresher2 = Fresher.builder().id(2L).name("John Smith").email("john2@email.com").build();
        List<Fresher> freshers = List.of(fresher1, fresher2);

        when(fresherService.searchByName("John")).thenReturn(freshers);

        // Act & Assert
        mockMvc.perform(get("/api/freshers/search/name")
                .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Search Fresher by name: John successfully"))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[1].name").value("John Smith"));
    }
}