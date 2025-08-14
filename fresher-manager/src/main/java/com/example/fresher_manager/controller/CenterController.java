package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.request.CenterRequest;
import com.example.fresher_manager.dto.respone.CenterResponse;
import com.example.fresher_manager.dto.respone.CommonResponse;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.service.CenterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse <CenterResponse>> createCenter(
        @RequestBody 
        CenterRequest request) {
            Center center = centerService.createCenter(request);
            CenterResponse response = CenterResponse.fromEntity(center);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponse<>(201, "Center created successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<CenterResponse>> updateCenter(
        @PathVariable Long id, 
        @RequestBody CenterRequest request) {
            Center center = centerService.updateCenter(id, request);
            CenterResponse response = CenterResponse.fromEntity(center);
        return ResponseEntity.ok(
            new CommonResponse<> (200, "Center updated successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> delete(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Center deleted successfully", null)
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CenterResponse>> getById(
        @PathVariable Long id) {
        Center center = centerService.getCenterById(id);
        CenterResponse response = CenterResponse.fromEntity(center);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Get Fresher by ID: " + id + " successfully", response));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<CenterResponse>>> getAll() {
        List<Center> centers = centerService.getAllCenters();
        List<CenterResponse> responses = centers.stream()
            .map(CenterResponse::fromEntity)
            .toList();
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Get all centers successfully", responses));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{centerId}/assign-fresher/{fresherId}")
    public ResponseEntity<CommonResponse<CenterResponse>> assignFresher(
        @PathVariable Long centerId,
        @PathVariable Long fresherId) {
        Center center = centerService.assignFresherToCenter(centerId, fresherId);
        CenterResponse response = CenterResponse.fromEntity(center);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Fresher assigned to center successfully", response));
    }
}
