package com.example.fresher_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fresher_manager.entity.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByName(String name);
}
