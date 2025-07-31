package com.example.fresher_manager.service;

import java.util.Map;

import com.example.fresher_manager.dto.DashboardDTO;

public interface DashboardService {
    long getTotalFresher();
    long getTotalProject();
    double getMaxAverageScore();
    double getMinAverageScore();
    Map<String, Long> getFresherCountByLanguage();
    Map<String, Long> getFresherCountByCenter();
    DashboardDTO getDashboardSummary();
}
