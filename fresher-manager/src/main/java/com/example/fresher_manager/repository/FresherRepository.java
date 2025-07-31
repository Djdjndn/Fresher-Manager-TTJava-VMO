package com.example.fresher_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fresher_manager.entity.Fresher;

public interface FresherRepository extends JpaRepository<Fresher, Long> {
    List<Fresher> findByNameContainingIgnoreCase(String name);
    List<Fresher> findByProgrammingLanguageContainingIgnoreCase(String programmingLanguage);
    Optional<Fresher> findByEmail(String email); // email là duy nhất
}
