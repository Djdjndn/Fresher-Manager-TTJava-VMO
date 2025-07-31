package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.entity.Assignment;

public interface AssignmentService {
    Assignment assignFresherToProject(Long fresherId, Long projectId);
    void removeFresherFromProject(Long fresherId, Long projectId);
    List<Assignment> getAssignmentsByFresher(Long fresherId);
    List<Assignment> getAssignmentsByProject(Long projectId);
}
