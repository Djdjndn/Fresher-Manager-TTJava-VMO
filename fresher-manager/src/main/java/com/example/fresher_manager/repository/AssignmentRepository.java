package com.example.fresher_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Assignment;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Project;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByFresher(Fresher fresher);
    List<Assignment> findByProject(Project project);
    boolean existsByFresherAndProject(Fresher fresher, Project project);
    void deleteByFresherAndProject(Fresher fresher, Project project);
}
