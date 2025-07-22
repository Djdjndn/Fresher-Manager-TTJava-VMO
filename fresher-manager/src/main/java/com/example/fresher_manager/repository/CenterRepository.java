package com.example.fresher_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Center;

public interface CenterRepository extends JpaRepository<Center, Long> {
    boolean existsByName(String name);
}
