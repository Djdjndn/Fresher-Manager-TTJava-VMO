package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.dto.CenterDTO;

public interface CenterService {
    CenterDTO createCenter(CenterDTO dto);
    CenterDTO updateCenter(Long id, CenterDTO dto);
    void deleteCenter(Long id);
    CenterDTO getCenterById(Long id);
    List<CenterDTO> getAllCenters();
    CenterDTO assignFresherToCenter(Long centerId, Long fresherId);
}
