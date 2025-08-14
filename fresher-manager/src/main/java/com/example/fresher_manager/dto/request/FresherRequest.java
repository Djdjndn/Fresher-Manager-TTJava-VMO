package com.example.fresher_manager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FresherRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "cannot be empty")
    private String email;

    @NotBlank(message = "can't be empty")
    private String programingLanguage;

    @NotNull(message = " can't be null")
    private Double score;
}

