package com.example.fresher_manager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterDTO {
    private Long id;
    private String name;
    private List<Long> fresherIds;
}
