package com.example.fresher_manager.dto.request;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    
    @NotBlank(message = "Project name cannot be empty")
    private String name;

    @NotBlank(message = "Manager cannot be empty")
    private String manager;

    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    @NotBlank(message = "Programming language cannot be empty")
    private String programmingLanguage;

    @NotBlank(message = "Status cannot be empty")
    private String status; // not_start, ongoing, canceled, closed

    @NotNull(message = "Center ID cannot be null")
    private Long centerId;

    private Set<Long> fresherIds;
}
