package com.example.fresher_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Fresher;

public interface FresherRepository extends JpaRepository<Fresher, Long> {
    List<Fresher> findByNameContainingIgnoreCase(String name);
    List<Fresher> findByProgrammingLanguage(String programmingLanguage);
    List<Fresher> findByEmail(String email);
}
