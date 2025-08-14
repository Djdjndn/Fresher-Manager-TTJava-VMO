package com.example.fresher_manager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ScoreRepository;
import com.example.fresher_manager.service.FresherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final FresherRepository fresherRepository;
    private final ScoreRepository scoreRepository;

    @Override
    public Fresher createFresher(FresherRequest request) {
        if(fresherRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("already exist " + request.getName());
        }
        Fresher newFresher =  Fresher.builder()
                .name(request.getName())
                .email(String.valueOf(request.getEmail()))
                .programingLanguage(request.getProgramingLanguage())
                .score(request.getScore())
                .build();
        return fresherRepository.save(newFresher);
    }

    @Override
    public Fresher updateFresher(Long id, FresherRequest request) {
        Fresher fresher = fresherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));
            fresher.setName(request.getName());
            fresher.setEmail(String.valueOf(request.getEmail()));
            fresher.setProgramingLanguage(request.getProgramingLanguage());
            fresher.setScore(request.getScore());
        return fresherRepository.save(fresher);
    }

    @Override
    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    @Override
    public Fresher getFresherById(Long id) {
        return fresherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));
    }

    @Override
    public List<Fresher> getAllFreshers() {
        List<Fresher> freshers = fresherRepository.findAll();
        if (freshers.isEmpty()) {
            throw new RuntimeException("No freshers found");
        }
        return freshers;
    }

    @Override
    public List<Fresher> searchByName(String name) {
        List<Fresher> freshers = fresherRepository.findByNameContainingIgnoreCase(name);
        if (freshers.isEmpty()) {   
            throw new RuntimeException("No freshers found with name: " + name);
        }
        return freshers;
    }

    @Override
    public List<Fresher> searchByProgramingLanguage(String language) {
        List<Fresher> freshers = fresherRepository.findByProgramingLanguageContainingIgnoreCase(language);
        if (freshers.isEmpty()){
            throw new RuntimeException("No freshers found with programming language: " + language);
        } else {
            return freshers.stream()
                .map(fresher -> Fresher.builder()
                    .id(fresher.getId())
                    .name(fresher.getName())
                    .email(fresher.getEmail())
                    .programingLanguage(fresher.getProgramingLanguage())
                    .build())
                .collect(Collectors.toList());
        }
    }

    @Override
    public Fresher searchByEmail(String email) {
        Fresher fresher = fresherRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Fresher not found with email: " + email));
        return fresher;
    }
}



