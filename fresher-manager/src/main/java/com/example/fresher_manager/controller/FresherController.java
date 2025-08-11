package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.FresherDTO;
import com.example.fresher_manager.service.FresherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/freshers")
@RequiredArgsConstructor
public class FresherController {

    private final FresherService fresherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FresherDTO> create(@RequestBody FresherDTO dto) {
        return ResponseEntity.ok(fresherService.createFresher(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FresherDTO> update(@PathVariable Long id, @RequestBody FresherDTO dto) {
        return ResponseEntity.ok(fresherService.updateFresher(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<FresherDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fresherService.getFresherById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<FresherDTO>> getAll() {
        return ResponseEntity.ok(fresherService.getAllFreshers());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/name")
    public ResponseEntity<List<FresherDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(fresherService.searchByName(name));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/language")
    public ResponseEntity<List<FresherDTO>> searchByLang(@RequestParam String language) {
        return ResponseEntity.ok(fresherService.searchByProgrammingLanguage(language));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/email")
    public ResponseEntity<FresherDTO> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(fresherService.searchByEmail(email));
    }
}
