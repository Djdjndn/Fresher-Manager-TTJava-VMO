package com.example.fresher_manager.dto.respone;

import java.util.Map;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Project;
import com.example.fresher_manager.entity.Score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private long totalFreshers;
    private long totalProjects;
    private double maxScore;
    private double minScore;
    private Map<String, Long> fresherCountByLanguage;
    private Map<String, Long> fresherCountByCenter;
    public static DashboardResponse fromEntity( Fresher fresher, Score score, Project project) {
        DashboardResponse response = new DashboardResponse();
            response.setTotalFreshers(fresher.getTotalFreshers());
            response.setTotalProjects(project.getTotalProjects());
            response.setMaxScore(score.getMaxScore());
            response.setMinScore(score.getMinScore());
            response.setFresherCountByLanguage(fresher.getFresherCountByLanguage());
            response.setFresherCountByCenter(fresher.getFresherCountByCenter());
            return response;
    }
}
