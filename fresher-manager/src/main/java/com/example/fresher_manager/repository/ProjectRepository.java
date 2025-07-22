package com.example.fresher_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Project;
import com.example.fresher_manager.entity.ProjectStatus;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByManagerName(String managerName);
    List<Project> findByStatus(ProjectStatus status);
}
