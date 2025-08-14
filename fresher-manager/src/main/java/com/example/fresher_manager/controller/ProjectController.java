package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.request.ProjectRequest;
import com.example.fresher_manager.dto.respone.CommonResponse;
import com.example.fresher_manager.dto.respone.ProjectResponse;
import com.example.fresher_manager.entity.Project;
import com.example.fresher_manager.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse<ProjectResponse>> createProject(
        @RequestBody ProjectRequest request) {

        Project project = projectService.createProject(request);
        ProjectResponse response = ProjectResponse.fromEntity(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponse<>(201, "Project created successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ProjectResponse>> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequest request) {

        Project project = projectService.updateProject(id, request);
        ProjectResponse response = ProjectResponse.fromEntity(project);

        return ResponseEntity.ok(
                new CommonResponse<>(200, "Project updated successfully", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProjectResponse>> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        ProjectResponse response = ProjectResponse.fromEntity(project);

        return ResponseEntity.ok(
                new CommonResponse<>(200, "Get project successfully", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<ProjectResponse>>> getAllProjects() {
    List<ProjectResponse> responses = projectService.getAllProjects() // trả về List<Project>
                .stream()
                .map(ProjectResponse::fromEntity) // chuyển từng Project -> ProjectResponse
                .toList();

        return ResponseEntity.ok(
                new CommonResponse<>(200, "Get all projects successfully", responses)
        );
        }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(
                new CommonResponse<>(200, "Project deleted successfully", null));
    }
}
