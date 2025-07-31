package com.example.fresher_manager.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.DashboardDTO;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Score;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ProjectRepository;
import com.example.fresher_manager.repository.ScoreRepository;
import com.example.fresher_manager.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final FresherRepository fresherRepository;
    private final ScoreRepository scoreRepository;
    private final ProjectRepository projectRepository;

    @Override
    public long getTotalFresher() {
        return fresherRepository.count();
    }

    @Override
    public long getTotalProject() {
        return projectRepository.count();
    }

    @Override
    public double getMaxAverageScore() {
        return fresherRepository.findAll().stream()
                .mapToDouble(f -> average(scoreRepository.findByFresherId(f.getId())))
                .max().orElse(0.0);
    }

    @Override
    public double getMinAverageScore() {
        return fresherRepository.findAll().stream()
                .mapToDouble(f -> average(scoreRepository.findByFresherId(f.getId())))
                .min().orElse(0.0);
    }

    @Override
    public Map<String, Long> getFresherCountByLanguage() {
        return fresherRepository.findAll().stream()
                .collect(Collectors.groupingBy(Fresher::getProgrammingLanguage, Collectors.counting()));
    }

    @Override
    public Map<String, Long> getFresherCountByCenter() {
        return fresherRepository.findAll().stream()
                .collect(Collectors.groupingBy(f -> f.getCenter().getName(), Collectors.counting()));
    }

    private double average(List<Score> scores) {
        return scores.stream().mapToDouble(Score::getScore).average().orElse(0.0);
    }
    @Override
    public DashboardDTO getDashboardSummary() {
        return new DashboardDTO(
            getTotalFresher(),
            getTotalProject(),
            getMaxAverageScore(),
            getMinAverageScore(),
            getFresherCountByLanguage(),
            getFresherCountByCenter()
    );
}
}
