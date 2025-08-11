package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.entity.Score;
import com.example.fresher_manager.service.ScoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fresher/{fresherId}")
    public ResponseEntity<Score> createScore(
            @PathVariable Long fresherId,
            @RequestBody Score score) {
        Score created = scoreService.createScore(fresherId, score);
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{scoreId}")
    public ResponseEntity<Score> updateScore(
            @PathVariable Long scoreId,
            @RequestBody Score score) {
        Score updated = scoreService.updateScore(scoreId, score);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{scoreId}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long scoreId) {
        scoreService.deleteScore(scoreId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/fresher/{fresherId}")
    public ResponseEntity<List<Score>> getScoresByFresher(@PathVariable Long fresherId) {
        List<Score> scores = scoreService.getScoresByFresher(fresherId);
        return ResponseEntity.ok(scores);
    }
}
