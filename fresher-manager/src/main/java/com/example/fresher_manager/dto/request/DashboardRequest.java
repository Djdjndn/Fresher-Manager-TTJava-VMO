package com.example.fresher_manager.dto.request;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardRequest {
    private long totalFreshers;
    private long totalProjects;
    private double maxAverageScore;
    private double minAverageScore;
    private Map<String, Long> fresherCountByLanguage;
    private Map<String, Long> fresherCountByCenter;
}
