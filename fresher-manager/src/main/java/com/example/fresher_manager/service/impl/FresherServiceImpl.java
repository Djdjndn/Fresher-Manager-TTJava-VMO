package com.example.fresher_manager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.FresherDTO;
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

    private FresherDTO mapToDTO(Fresher fresher) {
        List<Score> scores = scoreRepository.findByFresherId(fresher.getId());
        double averageScore = scores.stream()
            .mapToDouble(Score::getScore)
            .average()
            .orElse(0.0);

        return new FresherDTO(
            fresher.getId(),
            fresher.getName(),
            fresher.getEmail(),
            fresher.getProgrammingLanguage(),
            averageScore
        );
    }

    private Fresher mapToEntity(FresherDTO dto) {
        return new Fresher(
            dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getProgrammingLanguage(),
            null // Center sẽ gán sau nếu cần
        );
    }

    @Override
    public FresherDTO createFresher(FresherDTO dto) {
        Fresher fresher = fresherRepository.save(mapToEntity(dto));
        return mapToDTO(fresher);
    }

    @Override
    public FresherDTO updateFresher(Long id, FresherDTO dto) {
        Fresher fresher = fresherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));

        fresher.setName(dto.getName());
        fresher.setEmail(dto.getEmail());
        fresher.setProgrammingLanguage(dto.getProgrammingLanguage());

        return mapToDTO(fresherRepository.save(fresher));
    }

    @Override
    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    @Override
    public FresherDTO getFresherById(Long id) {
        return fresherRepository.findById(id)
            .map(this::mapToDTO)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));
    }

    @Override
    public List<FresherDTO> getAllFreshers() {
        return fresherRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<FresherDTO> searchByName(String name) {
        return fresherRepository.findByNameContainingIgnoreCase(name).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<FresherDTO> searchByProgrammingLanguage(String language) {
        return fresherRepository.findByProgrammingLanguageContainingIgnoreCase(language).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public FresherDTO searchByEmail(String email) {
        return fresherRepository.findByEmail(email)
            .map(this::mapToDTO)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));
    }
}
