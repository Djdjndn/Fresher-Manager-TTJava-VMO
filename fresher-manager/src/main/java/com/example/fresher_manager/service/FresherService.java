package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.dto.FresherDTO;

public interface FresherService {
    FresherDTO createFresher(FresherDTO fresherDTO);
    FresherDTO updateFresher(Long id, FresherDTO fresherDTO);
    void deleteFresher(Long id);
    FresherDTO getFresherById(Long id);

    List<FresherDTO> getAllFreshers();
    List<FresherDTO> searchByName(String name);
    List<FresherDTO> searchByProgrammingLanguage(String language);
    FresherDTO searchByEmail(String email);
}
