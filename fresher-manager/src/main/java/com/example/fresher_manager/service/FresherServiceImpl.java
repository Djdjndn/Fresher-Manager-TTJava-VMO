package com.example.fresher_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.repository.FresherRepository;

@Service
public class FresherServiceImpl implements FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Override
    public Fresher createFresher(Fresher fresher) {
        return fresherRepository.save(fresher);
    }

    @Override
    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    @Override
    public Optional<Fresher> getFresherById(Long id) {
        return fresherRepository.findById(id);
    }

    @Override
    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    @Override
    public List<Fresher> searchByName(String name) {
        return fresherRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Fresher> searchByProgrammingLanguage(String language) {
        return fresherRepository.findByProgrammingLanguage(language);
    }
}
