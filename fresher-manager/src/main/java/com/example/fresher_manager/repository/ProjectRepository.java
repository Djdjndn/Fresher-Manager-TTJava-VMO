package com.example.fresher_manager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProgrammingLanguage(String language);
    List<Project> findByStatus(String status);
    List<Project> findByManager(String manager);
}
