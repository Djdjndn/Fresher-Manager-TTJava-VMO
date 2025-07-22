package com.example.fresher_manager.service;

import java.util.List;
import java.util.Optional;

import com.example.fresher_manager.entity.Fresher;

public interface FresherService {
    Fresher createFresher(Fresher fresher);
    List<Fresher> getAllFreshers();
    Optional<Fresher> getFresherById(Long id);
    void deleteFresher(Long id);
    List<Fresher> searchByName(String name);
    List<Fresher> searchByProgrammingLanguage(String language);
}
