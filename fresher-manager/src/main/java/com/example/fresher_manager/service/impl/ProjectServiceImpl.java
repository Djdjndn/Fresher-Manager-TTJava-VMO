package com.example.fresher_manager.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fresher_manager.dto.request.ProjectRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Project;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ProjectRepository;
import com.example.fresher_manager.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final FresherRepository fresherRepository;
    private final CenterRepository centerRepository;

    @Override
    public Project createProject(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setManager(request.getManager());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setProgrammingLanguage(request.getProgrammingLanguage());
        project.setStatus(request.getStatus());

        // Gán center
        if (request.getCenterId() != null) {
            Center center = centerRepository.findById(request.getCenterId())
                    .orElseThrow(() -> new RuntimeException("Center not found with id " + request.getCenterId()));
            project.setCenter(center);
        }

        // Gán fresher
        if (request.getFresherIds() != null && !( request.getFresherIds()).isEmpty()) {
            Set<Fresher> freshers = request.getFresherIds().stream()
                    .map(id -> fresherRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Fresher not found with id " + id)))
                    .collect(Collectors.toSet());
            project.setFreshers(freshers);
        } else {
            project.setFreshers(new HashSet<>());
        }

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectRequest request) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        existing.setName(request.getName());
        existing.setManager(request.getManager());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setProgrammingLanguage(request.getProgrammingLanguage());
        existing.setStatus(request.getStatus());

        if (request.getCenterId() != null) {
            Center center = centerRepository.findById(request.getCenterId())
                    .orElseThrow(() -> new RuntimeException("Center not found with id " + request.getCenterId()));
            existing.setCenter(center);
        }

        if (request.getFresherIds() != null) {
            Set<Fresher> freshers = request.getFresherIds().stream()
                    .map(fid -> fresherRepository.findById(fid)
                            .orElseThrow(() -> new RuntimeException("Fresher not found with id " + fid)))
                    .collect(Collectors.toSet());
            existing.setFreshers(freshers);
        }

        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project assignFresherToProject(Long projectId, Long fresherId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + projectId));

        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new RuntimeException("Fresher not found with id " + fresherId));

        if (project.getFreshers() == null) {
            project.setFreshers(new HashSet<>());
        }

        project.getFreshers().add(fresher);
        return projectRepository.save(project);
    }
}
