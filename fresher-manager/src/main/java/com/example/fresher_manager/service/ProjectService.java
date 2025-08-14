package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.dto.request.ProjectRequest;
import com.example.fresher_manager.entity.Project;

public interface ProjectService {
    Project createProject(ProjectRequest request);
    Project updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    Project assignFresherToProject(Long projectId, Long fresherId);
}