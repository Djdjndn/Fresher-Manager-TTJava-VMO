package com.example.fresher_manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.entity.Assignment;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Project;
import com.example.fresher_manager.repository.AssignmentRepository;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ProjectRepository;
import com.example.fresher_manager.service.AssignmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final FresherRepository fresherRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Assignment assignFresherToProject(Long fresherId, Long projectId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Kiểm tra đã phân chưa
        boolean alreadyAssigned = assignmentRepository.existsByFresherAndProject(fresher, project);
        if (alreadyAssigned) {
            throw new RuntimeException("Fresher already assigned to this project.");
        }

        Assignment assignment = new Assignment();
        assignment.setFresher(fresher);
        assignment.setProject(project);
        assignment.setTimes(1);

        return assignmentRepository.save(assignment);
    }

    @Override
    public void removeFresherFromProject(Long fresherId, Long projectId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        assignmentRepository.deleteByFresherAndProject(fresher, project);
    }

    @Override
    public List<Assignment> getAssignmentsByFresher(Long fresherId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));

        return assignmentRepository.findByFresher(fresher);
    }

    @Override
    public List<Assignment> getAssignmentsByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return assignmentRepository.findByProject(project);
    }
}
