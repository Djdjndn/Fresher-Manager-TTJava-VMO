package com.example.fresher_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByFresher(Fresher fresher);
}
