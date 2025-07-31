package com.example.fresher_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FresherDTO {
    private Long id;
    private String name;
    private String email;
    private String programmingLanguage;
    private Double averageScore; // tính từ 3 bài
}
