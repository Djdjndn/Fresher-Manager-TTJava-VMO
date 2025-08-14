package com.example.fresher_manager.service;

import java.util.List;

import com.example.fresher_manager.dto.request.CenterRequest;
import com.example.fresher_manager.entity.Center;

public interface CenterService {
    Center createCenter(CenterRequest request);
    Center updateCenter(Long id, CenterRequest request);
    void deleteCenter(Long id);
    Center getCenterById(Long id);
    List<Center> getAllCenters();
    Center assignFresherToCenter(Long centerId, Long fresherId);
}
