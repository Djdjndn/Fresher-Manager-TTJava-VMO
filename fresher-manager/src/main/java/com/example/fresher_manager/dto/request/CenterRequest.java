package com.example.fresher_manager.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    private List<Long> fresherIds;
}
