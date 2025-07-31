package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.entity.Score;

public interface ScoreService {
    Score createScore(Long fresherId, Score score);
    Score updateScore(Long scoreId, Score updatedScore);
    void deleteScore(Long scoreId);
    List<Score> getScoresByFresher(Long fresherId);
}
