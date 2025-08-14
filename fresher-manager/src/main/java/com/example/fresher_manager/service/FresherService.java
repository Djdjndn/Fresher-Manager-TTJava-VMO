package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.entity.Fresher;

public interface FresherService {
    Fresher createFresher(FresherRequest request);
    Fresher updateFresher(Long id, FresherRequest request);
    void deleteFresher(Long id);
    Fresher getFresherById(Long id);
    List<Fresher> getAllFreshers();
    List<Fresher> searchByName(String name);
    List<Fresher> searchByProgramingLanguage(String language);
    Fresher searchByEmail(String email);
}
