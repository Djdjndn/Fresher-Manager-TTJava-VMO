package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.CenterDTO;
import com.example.fresher_manager.service.CenterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PostMapping
    public ResponseEntity<CenterDTO> create(@RequestBody CenterDTO dto) {
        return ResponseEntity.ok(centerService.createCenter(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CenterDTO> update(@PathVariable Long id, @RequestBody CenterDTO dto) {
        return ResponseEntity.ok(centerService.updateCenter(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(centerService.getCenterById(id));
    }

    @GetMapping
    public ResponseEntity<List<CenterDTO>> getAll() {
        return ResponseEntity.ok(centerService.getAllCenters());
    }

    @PostMapping("/{centerId}/assign-fresher/{fresherId}")
    public ResponseEntity<CenterDTO> assignFresher(
            @PathVariable Long centerId,
            @PathVariable Long fresherId) {
        return ResponseEntity.ok(centerService.assignFresherToCenter(centerId, fresherId));
    }
}
