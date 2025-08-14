package com.example.fresher_manager.service;

import java.util.Map;

public interface DashboardService {
    long getTotalFresher();
    long getTotalProject();
    double getMaxScore();
    double getMinScore();
    Map<String, Long> getFresherCountByLanguage();
    Map<String, Long> getFresherCountByCenter();
}
