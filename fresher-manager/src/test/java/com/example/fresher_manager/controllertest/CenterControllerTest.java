package com.example.fresher_manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CenterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Tạo center
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createCenter_ShouldReturnCreated() throws Exception {
        String json = """
            {
                "name": "Ha Noi Center",
                "location": "Ha Noi"
            }
        """;
        mockMvc.perform(post("/api/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    // Lấy tất cả centers
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAllCenters_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/centers"))
               .andExpect(status().isOk());
    }

    // Xóa center
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteCenter_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/centers/1"))
               .andExpect(status().isNoContent());
    }
}
