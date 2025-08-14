package com.example.fresher_manager.dto.respone;

import java.time.LocalDate;
import java.util.List;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private String manager;
    private LocalDate startDate;
    private LocalDate endDate;
    private String programmingLanguage;
    private String status;
    private String centerName;
    private List<String> fresherNames;

    public static ProjectResponse fromEntity(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setManager(project.getManager());
        response.setStartDate(project.getStartDate());
        response.setEndDate(project.getEndDate());
        response.setProgrammingLanguage(project.getProgrammingLanguage());
        response.setStatus(project.getStatus());
        if (project.getCenter() != null) {
            response.setCenterName(project.getCenter().getName());
        }
        if (project.getFreshers() != null) {
            response.setFresherNames(
                project.getFreshers().stream()
                        .map(Fresher::getName)
                        .toList()
            );
        }
        return response;
    }
}

