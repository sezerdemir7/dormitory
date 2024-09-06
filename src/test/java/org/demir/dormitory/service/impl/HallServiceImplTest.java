package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.HallRequest;
import org.demir.dormitory.dto.request.HallUpdateRequest;
import org.demir.dormitory.dto.response.HallResponse;
import org.demir.dormitory.entity.Hall;
import org.demir.dormitory.entity.Staff;
import org.demir.dormitory.entity.enumType.StaffRole;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.HallRepository;
import org.demir.dormitory.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HallServiceImplTest {

    private HallRepository hallRepository;
    private StaffService staffService;
    private HallServiceImpl hallService;

    @BeforeEach
    void setUp() {
        hallRepository = Mockito.mock(HallRepository.class);
        staffService = Mockito.mock(StaffService.class);
        hallService = new HallServiceImpl(hallRepository, staffService);
    }




    @Test
    void deleteHall_Success() {
        Long hallId = 1L;
        Hall hall = new Hall();
        hall.setId(hallId);

        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));

        hallService.deleteHall(hallId);

        assertTrue(hall.isDeleted());
        verify(hallRepository).save(hall);
    }

    @Test
    void deleteHall_NotFound() {
        Long hallId = 1L;

        when(hallRepository.findById(hallId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hallService.deleteHall(hallId));

        verify(hallRepository, never()).save(any(Hall.class));
    }



    @Test
    void getHallByName_NotFound() {
        String hallName = "Nonexistent Hall";

        when(hallRepository.findByName(hallName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hallService.getHallByName(hallName));

        verify(hallRepository).findByName(hallName);
    }


    @Test
    void updateHall_NotFound() {
        HallUpdateRequest request = new HallUpdateRequest(1L, "Updated Hall", "Updated Location", 200);

        when(hallRepository.findById(request.id())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hallService.updateHall(request));

        verify(hallRepository, never()).save(any(Hall.class));
    }

    @Test
    void getHallById_Success() {
        Hall hall = new Hall();
        hall.setId(1L);
        hall.setName("Test Hall");

        when(hallRepository.findById(hall.getId())).thenReturn(Optional.of(hall));

        Hall result = hallService.getHallById(hall.getId());
        assertEquals(hall.getId(), result.getId());
        assertEquals(hall.getName(), result.getName());
        verify(hallRepository).findById(hall.getId());
    }

    @Test
    void getHallById_NotFound() {
        Long hallId = 1L;
        when(hallRepository.findById(hallId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hallService.getHallById(hallId));

        verify(hallRepository).findById(hallId);
    }
}
