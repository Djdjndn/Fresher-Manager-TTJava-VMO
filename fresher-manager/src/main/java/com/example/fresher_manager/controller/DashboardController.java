package com.example.fresher_manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.respone.CommonResponse;
import com.example.fresher_manager.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<CommonResponse<Map<String, Object>>> getDashboardSummary() {
    Map<String, Object> summary = new HashMap<>();
    summary.put("totalFreshers", dashboardService.getTotalFresher());
    summary.put("totalProjects", dashboardService.getTotalProject());
    summary.put("maxScore", dashboardService.getMaxScore());
    summary.put("minScore", dashboardService.getMinScore());
    summary.put("fresherCountByLanguage", dashboardService.getFresherCountByLanguage());
    summary.put("fresherCountByCenter", dashboardService.getFresherCountByCenter());

    return ResponseEntity.ok(
        new CommonResponse<>(200, "Dashboard summary retrieved successfully", summary)
    );
}
}
