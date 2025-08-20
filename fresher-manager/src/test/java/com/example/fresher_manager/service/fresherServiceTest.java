package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.request.FresherRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Score;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.ScoreRepository;
import com.example.fresher_manager.service.impl.FresherServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class fresherServiceImplTest {

    @Mock
    private FresherRepository fresherRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private FresherServiceImpl fresherService;

    @Test
    void createFresher_WithValidRequest_ShouldReturnSavedFresher() {
        // Arrange
        FresherRequest request = new FresherRequest("John Doe", "john@email.com", "Java", 8.5);
        Fresher fresher = Fresher.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .programingLanguage("Java")
                .score(8.5)
                .build();

        when(fresherRepository.existsByName("John Doe")).thenReturn(false);
        when(fresherRepository.save(any(Fresher.class))).thenReturn(fresher);
        when(scoreRepository.save(any(Score.class))).thenReturn(Score.builder().build());

        // Act
        Fresher result = fresherService.createFresher(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@email.com", result.getEmail());
        assertEquals("Java", result.getProgramingLanguage());
        assertEquals(8.5, result.getScore());
        verify(fresherRepository, times(1)).save(any(Fresher.class));
        verify(scoreRepository, times(1)).save(any(Score.class));
    }

    @Test
    void updateFresher_WhenFresherExists_ShouldReturnUpdatedFresher() {
        // Arrange
        FresherRequest request = new FresherRequest("Updated Name", "updated@email.com", "Python", 9.0);
        Fresher existingFresher = Fresher.builder()
                .id(1L)
                .name("Old Name")
                .email("old@email.com")
                .programingLanguage("Java")
                .score(8.0)
                .build();

        when(fresherRepository.findById(1L)).thenReturn(Optional.of(existingFresher));
        when(fresherRepository.save(any(Fresher.class))).thenReturn(existingFresher);
        when(scoreRepository.findByFresherId(1L)).thenReturn(List.of());

        // Act
        Fresher result = fresherService.updateFresher(1L, request);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("updated@email.com", result.getEmail());
        assertEquals("Python", result.getProgramingLanguage());
        assertEquals(9.0, result.getScore());
        verify(fresherRepository, times(1)).save(existingFresher);
    }

    @Test
    void getFresherById_WhenFresherExists_ShouldReturnFresher() {
        // Arrange
        Fresher fresher = Fresher.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .programingLanguage("Java")
                .score(8.5)
                .build();

        when(fresherRepository.findById(1L)).thenReturn(Optional.of(fresher));

        // Act
        Fresher result = fresherService.getFresherById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@email.com", result.getEmail());
    }

    @Test
    void searchByName_WhenFreshersExist_ShouldReturnFresherList() {
        // Arrange
        Fresher fresher1 = Fresher.builder().id(1L).name("John Doe").email("john1@email.com").build();
        Fresher fresher2 = Fresher.builder().id(2L).name("John Smith").email("john2@email.com").build();
        List<Fresher> freshers = List.of(fresher1, fresher2);

        when(fresherRepository.findByNameContainingIgnoreCase("John")).thenReturn(freshers);

        // Act
        List<Fresher> result = fresherService.searchByName("John");

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("John Smith", result.get(1).getName());
    }

    @Test
    void searchByEmail_WhenFresherExists_ShouldReturnFresher() {
        // Arrange
        Fresher fresher = Fresher.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .programingLanguage("Java")
                .score(8.5)
                .build();

        when(fresherRepository.findByEmail("john@email.com")).thenReturn(Optional.of(fresher));

        // Act
        Fresher result = fresherService.searchByEmail("john@email.com");

        // Assert
        assertNotNull(result);
        assertEquals("john@email.com", result.getEmail());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void searchByProgramingLanguage_WhenFreshersExist_ShouldReturnFresherList() {
        // Arrange
        Fresher fresher1 = Fresher.builder().id(1L).name("John Doe").programingLanguage("Java").build();
        Fresher fresher2 = Fresher.builder().id(2L).name("Jane Smith").programingLanguage("Java").build();
        List<Fresher> freshers = List.of(fresher1, fresher2);

        when(fresherRepository.findByProgramingLanguageContainingIgnoreCase("Java")).thenReturn(freshers);

        // Act
        List<Fresher> result = fresherService.searchByProgramingLanguage("Java");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getProgramingLanguage());
        assertEquals("Java", result.get(1).getProgramingLanguage());
    }
}