package com.example.fresher_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByFresherId(Long fresherId);
}
