package com.example.fresher_manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.request.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.service.CenterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final FresherRepository fresherRepository;

    @Override
    public Center createCenter(CenterRequest request) { 
        Center newcenter =  Center.builder()
                .name(request.getName())
                .build();
        return centerRepository.save(newcenter);
    }

    @Override
    public Center updateCenter(Long id, CenterRequest request) {
        Center center = centerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Center not found"));
            center.setName(request.getName());
        return centerRepository.save(center);
    }

    @Override
    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }

    @Override
    public Center getCenterById(Long id) {
        return centerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Center not found"));
    }

    @Override
    public List<Center> getAllCenters() {
        List<Center> centers = centerRepository.findAll();
        if (centers.isEmpty()) {
            throw new RuntimeException("No freshers found");
        }
        return centers;
    }

    @Override
    public Center assignFresherToCenter(Long centerId, Long fresherId) {
        Center center = centerRepository.findById(centerId)
            .orElseThrow(() -> new RuntimeException("Center not found"));
        Fresher fresher = fresherRepository.findById(fresherId)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));

        fresher.setCenter(center);
        fresherRepository.save(fresher);

        return getCenterById(centerId);
    }
}
