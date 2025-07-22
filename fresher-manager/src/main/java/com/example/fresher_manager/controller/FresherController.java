package com.example.fresher_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.service.FresherService;

@RestController
@RequestMapping("/api/freshers")
public class FresherController {

    @Autowired
    private FresherService fresherService;

    @PostMapping
    public ResponseEntity<Fresher> create(@RequestBody Fresher fresher) {
        return ResponseEntity.ok(fresherService.createFresher(fresher));
    }

    @GetMapping
    public ResponseEntity<List<Fresher>> getAll() {
        return ResponseEntity.ok(fresherService.getAllFreshers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fresher> getById(@PathVariable Long id) {
        return fresherService.getFresherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Fresher>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(fresherService.searchByName(name));
    }

    @GetMapping("/language")
    public ResponseEntity<List<Fresher>> searchByLanguage(@RequestParam String lang) {
        return ResponseEntity.ok(fresherService.searchByProgrammingLanguage(lang));
    }
}
