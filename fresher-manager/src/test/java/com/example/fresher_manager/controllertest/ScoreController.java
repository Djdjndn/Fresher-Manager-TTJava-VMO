package com.example.fresher_manager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Tạo điểm
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createScore_ShouldReturnOk() throws Exception {
        String json = """
            {
                "subject": "Java",
                "score": 85
            }
        """;
        mockMvc.perform(post("/api/scores/fresher/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    // Cập nhật điểm
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateScore_ShouldReturnOk() throws Exception {
        String json = """
            {
                "subject": "Java",
                "score": 90
            }
        """;
        mockMvc.perform(put("/api/scores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    // Xóa điểm
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteScore_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/scores/1"))
               .andExpect(status().isNoContent());
    }

    // Lấy điểm của Fresher
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getScoresByFresher_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/scores/fresher/1"))
               .andExpect(status().isOk());
    }
}
