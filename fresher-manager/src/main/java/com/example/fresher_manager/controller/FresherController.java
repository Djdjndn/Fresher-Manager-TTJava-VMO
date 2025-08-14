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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.dto.respone.CommonResponse;
import com.example.fresher_manager.dto.respone.FresherResponse;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.service.FresherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/freshers")
@RequiredArgsConstructor
public class FresherController {

    private final FresherService fresherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse <FresherResponse>> createFresher(
        @Valid
        @RequestBody FresherRequest request) {
        Fresher fresher = fresherService.createFresher(request);
        FresherResponse response = FresherResponse.fromEntity(fresher);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponse<>(201, "Fresher created successfully", response));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse <FresherResponse>> update(
        @PathVariable Long id, 
        @RequestBody FresherRequest request) {
        Fresher fresher = fresherService.updateFresher(id, request);
        FresherResponse response = FresherResponse.fromEntity(fresher);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Fresher updated successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse< Void>> delete(@PathVariable Long id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Fresher deleted successfully", null)
        );
            
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse <FresherResponse>> getById(@PathVariable Long id) {
        Fresher fresher = fresherService.getFresherById(id);
        FresherResponse response = FresherResponse.fromEntity(fresher);
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Get Fresher by ID: " + id + " successfully", response)
        ) ;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<FresherResponse>>> getAll() {
    List<Fresher> freshers = fresherService.getAllFreshers();

    List<FresherResponse> responses = freshers.stream()
            .map(FresherResponse::fromEntity) // chuyển từng Fresher sang FresherResponse
            .toList(); // hoặc .collect(Collectors.toList())
    return ResponseEntity.ok(
            new CommonResponse<>(200, "Get all Freshers", responses)
    );
}


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/name")
    public ResponseEntity<CommonResponse<List<FresherResponse>>> searchByName(@RequestParam String name) {
    List<Fresher> freshers = fresherService.searchByName(name);
    List<FresherResponse> responses = freshers.stream()
            .map(FresherResponse::fromEntity)
            .toList();
    return ResponseEntity.ok(
            new CommonResponse<>(200, "Search Fresher by name: " + name + " successfully", responses)
    );
}


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/language")
    public ResponseEntity<CommonResponse<List<FresherResponse>>> searchByLang(@RequestParam String language) {
        List<Fresher> freshers = fresherService.searchByProgramingLanguage(language);
        List<FresherResponse> responses = freshers.stream()
            .map(FresherResponse::fromEntity)
            .toList();
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Search Fresher by program language: " + language + " successfully", responses));
}


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search/email")
    public ResponseEntity<CommonResponse<FresherResponse>> searchByEmail(@RequestParam String email) {
        Fresher freshers = fresherService.searchByEmail(email);
        FresherResponse response = FresherResponse.fromEntity(freshers);
        return ResponseEntity.ok(new CommonResponse<> (200, "Search Fresher by email: " + email + " successfully", response));
    }
}
