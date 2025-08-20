package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.request.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.service.impl.CenterServiceImpl;

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
class centerServiceTest {

    @Mock
    private CenterRepository centerRepository;

    @Mock
    private FresherRepository fresherRepository;

    @InjectMocks
    private CenterServiceImpl centerService;

    @Test
    void createCenter_WithValidRequest_ShouldReturnSavedCenter() {
        CenterRequest request = new CenterRequest("Center A", null);
        Center center = Center.builder()
                .id(1L)
                .name("Center A")
                .build();

        when(centerRepository.save(any(Center.class))).thenReturn(center);

        // Act
        Center result = centerService.createCenter(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Center A", result.getName());
        verify(centerRepository, times(1)).save(any(Center.class));
    }

    @Test
    void updateCenter_WhenCenterExists_ShouldReturnUpdatedCenter() {
        // Arrange
        CenterRequest request = new CenterRequest("Updated Center", null);
        Center existingCenter = Center.builder()
                .id(1L)
                .name("Old Center")
                .build();
        Center updatedCenter = Center.builder()
                .id(1L)
                .name("Updated Center")
                .build();

        when(centerRepository.findById(1L)).thenReturn(Optional.of(existingCenter));
        when(centerRepository.save(any(Center.class))).thenReturn(updatedCenter);

        // Act
        Center result = centerService.updateCenter(1L, request);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Center", result.getName());
        verify(centerRepository, times(1)).findById(1L);
        verify(centerRepository, times(1)).save(existingCenter);
    }

    @Test
    void updateCenter_WhenCenterNotFound_ShouldThrowException() {
        // Arrange
        CenterRequest request = new CenterRequest("Updated Center", null);
        when(centerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> centerService.updateCenter(1L, request));
        verify(centerRepository, times(1)).findById(1L);
        verify(centerRepository, never()).save(any());
    }

    @Test
    void getCenterById_WhenCenterExists_ShouldReturnCenter() {
        // Arrange
        Center center = Center.builder()
                .id(1L)
                .name("Center A")
                .build();

        when(centerRepository.findById(1L)).thenReturn(Optional.of(center));

        // Act
        Center result = centerService.getCenterById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Center A", result.getName());
    }

    @Test
    void getAllCenters_WhenCentersExist_ShouldReturnCenterList() {
        // Arrange
        Center center1 = Center.builder().id(1L).name("Center A").build();
        Center center2 = Center.builder().id(2L).name("Center B").build();
        List<Center> centers = List.of(center1, center2);

        when(centerRepository.findAll()).thenReturn(centers);

        // Act
        List<Center> result = centerService.getAllCenters();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Center A", result.get(0).getName());
        assertEquals("Center B", result.get(1).getName());
    }

    @Test
    void assignFresherToCenter_WhenBothExist_ShouldReturnCenter() {
        // Arrange
        Center center = Center.builder().id(1L).name("Center A").build();
        Fresher fresher = Fresher.builder().id(100L).name("John Doe").build();

        when(centerRepository.findById(1L)).thenReturn(Optional.of(center));
        when(fresherRepository.findById(100L)).thenReturn(Optional.of(fresher));
        when(fresherRepository.save(any(Fresher.class))).thenReturn(fresher);
        when(centerRepository.findById(1L)).thenReturn(Optional.of(center));

        // Act
        Center result = centerService.assignFresherToCenter(1L, 100L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(fresherRepository, times(1)).save(fresher);
    }
}