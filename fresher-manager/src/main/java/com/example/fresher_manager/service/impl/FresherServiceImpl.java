package com.example.fresher_manager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Score;
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
        if (fresherRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("already exist " + request.getName());
        }

        Fresher newFresher = Fresher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .programingLanguage(request.getProgramingLanguage())
                .score(request.getScore())
                .build();

        Fresher savedFresher = fresherRepository.save(newFresher);

        // Nếu có nhập điểm, tạo score mới
        if (request.getScore() != null) {
            Score score = Score.builder()
                    .score(request.getScore())
                    .fresher(savedFresher)
                    .build();
            scoreRepository.save(score);
        }

        return savedFresher;
    }
    @Override
    public Fresher updateFresher(Long id, FresherRequest request) {
        Fresher fresher = fresherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));

        fresher.setName(request.getName());
        fresher.setEmail(request.getEmail());
        fresher.setProgramingLanguage(request.getProgramingLanguage());
        fresher.setScore(request.getScore());

        Fresher updatedFresher = fresherRepository.save(fresher);

        List<Score> scores = scoreRepository.findByFresherId(id);

        if (scores.isEmpty()) {
            // Chưa có → tạo mới
            Score newScore = Score.builder()
                    .score(request.getScore())
                    .fresher(updatedFresher)
                    .build();
            scoreRepository.save(newScore);
        } else {
            // Đã có → cập nhật bản ghi đầu tiên
            Score score = scores.get(0);
            score.setScore(request.getScore());
            scoreRepository.save(score);
        }

        return updatedFresher;
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



