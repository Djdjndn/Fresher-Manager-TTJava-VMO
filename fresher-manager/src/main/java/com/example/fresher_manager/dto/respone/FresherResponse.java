package com.example.fresher_manager.dto.respone;

import com.example.fresher_manager.entity.Fresher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FresherResponse {
    private Long id;
    private String name;
    private String email;
    private String programingLanguage;
    private Double score; // tính từ 3 bài
    public static FresherResponse fromEntity(Fresher fresher) {
        FresherResponse response = new FresherResponse();
        response.setId(fresher.getId());
        response.setName(fresher.getName());
        response.setEmail(fresher.getEmail());
        response.setProgramingLanguage(fresher.getProgramingLanguage());
        response.setScore(fresher.getScore());
        return response;
    }
}
