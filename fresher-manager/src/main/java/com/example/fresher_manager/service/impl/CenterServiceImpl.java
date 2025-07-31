package com.example.fresher_manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fresher_manager.dto.CenterDTO;
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

    private CenterDTO mapToDTO(Center center) {
        List<Long> fresherIds = center.getFreshers() == null ? List.of() :
            center.getFreshers().stream().map(Fresher::getId).toList();
        return new CenterDTO(center.getId(), center.getName(), fresherIds);
    }

    @Override
    public CenterDTO createCenter(CenterDTO dto) {
        Center center = new Center(null, dto.getName(), new ArrayList<>());
        return mapToDTO(centerRepository.save(center));
    }

    @Override
    public CenterDTO updateCenter(Long id, CenterDTO dto) {
        Center center = centerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Center not found"));
        center.setName(dto.getName());
        return mapToDTO(centerRepository.save(center));
    }

    @Override
    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }

    @Override
    public CenterDTO getCenterById(Long id) {
        return centerRepository.findById(id)
            .map(this::mapToDTO)
            .orElseThrow(() -> new RuntimeException("Center not found"));
    }

    @Override
    public List<CenterDTO> getAllCenters() {
        return centerRepository.findAll().stream()
            .map(this::mapToDTO).toList();
    }

    @Override
    public CenterDTO assignFresherToCenter(Long centerId, Long fresherId) {
        Center center = centerRepository.findById(centerId)
            .orElseThrow(() -> new RuntimeException("Center not found"));
        Fresher fresher = fresherRepository.findById(fresherId)
            .orElseThrow(() -> new RuntimeException("Fresher not found"));

        fresher.setCenter(center);
        fresherRepository.save(fresher);

        return getCenterById(centerId);
    }
}
