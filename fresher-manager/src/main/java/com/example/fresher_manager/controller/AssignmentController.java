package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.entity.Assignment;
import com.example.fresher_manager.service.AssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<Assignment> assignFresherToProject(
            @RequestParam Long fresherId,
            @RequestParam Long projectId) {
        Assignment assignment = assignmentService.assignFresherToProject(fresherId, projectId);
        return ResponseEntity.ok(assignment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFresherFromProject(
            @RequestParam Long fresherId,
            @RequestParam Long projectId) {
        assignmentService.removeFresherFromProject(fresherId, projectId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/by-fresher/{fresherId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByFresher(@PathVariable Long fresherId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByFresher(fresherId);
        return ResponseEntity.ok(assignments);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByProject(@PathVariable Long projectId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByProject(projectId);
        return ResponseEntity.ok(assignments);
    }
}
