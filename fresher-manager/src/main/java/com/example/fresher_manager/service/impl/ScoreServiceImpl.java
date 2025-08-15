package com.example.fresher_manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Score;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ScoreRepository;
import com.example.fresher_manager.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final FresherRepository fresherRepository;

    @Override
    public Score createScore(Long fresherId, Score score) {
        Fresher fresher = fresherRepository.findById(fresherId)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));

        // Gán fresher cho score
        score.setFresher(fresher);
        scoreRepository.save(score);

        // Cập nhật thẳng điểm cho fresher
        fresher.setScore(score.getScore());
        fresherRepository.save(fresher);

        return score;
    }

    @Override
    public Score updateScore(Long scoreId, Score updatedScore) {
        Score existing = scoreRepository.findById(scoreId)
            .orElseThrow(() -> new RuntimeException("Score not found"));

        existing.setScore(updatedScore.getScore());
        scoreRepository.save(existing);

        // Đồng thời cập nhật Fresher.score
        Fresher fresher = existing.getFresher();
        fresher.setScore(updatedScore.getScore());
        fresherRepository.save(fresher);

        return existing;
    }

    @Override
    public void deleteScore(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }

    @Override
    public List<Score> getScoresByFresher(Long fresherId) {
        return scoreRepository.findByFresherId(fresherId);
    }
}
