package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.GardenRequest;
import org.demir.dormitory.dto.response.GardenResponse;
import org.demir.dormitory.entity.Garden;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.GardenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GardenServiceImplTest {
    private GardenRepository gardenRepository;
    private GardenServiceImpl gardenService;


    @BeforeEach
    void setUp() {
        gardenRepository = Mockito.mock(GardenRepository.class);
        gardenService = new GardenServiceImpl(gardenRepository);
    }


    @Test
    void saveGarden() {
        GardenRequest request = new GardenRequest("test name", "test location");

        Garden toSave = new Garden();
        toSave.setName(request.name());
        toSave.setLocation(request.location());

        Garden savedGarden = new Garden();
        savedGarden.setId(1L);
        savedGarden.setName(toSave.getName());
        savedGarden.setLocation(toSave.getLocation());

        when(gardenRepository.save(toSave)).thenReturn(savedGarden);
        GardenResponse response = gardenService.saveGarden(request);

        assertEquals(savedGarden.getId(), response.id());
        assertEquals(savedGarden.getName(), response.name());
        assertEquals(savedGarden.getLocation(), response.location());

        verify(gardenRepository).save(toSave);
    }

    @Test
    void deleteGarden() {
        Long id = 1L;
        Garden garden = new Garden();
        garden.setId(id);

        when(gardenRepository.findById(id)).thenReturn(Optional.of(garden));

        gardenService.deleteGarden(id);
        assertTrue(garden.isDeleted());
        verify(gardenRepository).save(garden);
    }

    @Test
    void getGardenByName_Success() {
        Garden garden = new Garden();
        garden.setId(1L);
        garden.setName("test name");

        when(gardenRepository.findByName(garden.getName())).thenReturn(Optional.of(garden));

        GardenResponse response = gardenService.getGardenByName(garden.getName());

        assertEquals(garden.getId(), response.id());
        assertEquals(garden.getName(), response.name());

        verify(gardenRepository).findByName(garden.getName());

    }

    @Test
    void getGardenByName_NotFound() {
        String name = "test name";

        when(gardenRepository.findByName(name)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> gardenService.getGardenByName(name));

        assertEquals(exception.getMessage(), "Garden not found!.");
        verify(gardenRepository).findByName(name);
    }

    @Test
    void whenShouldReturnAllGardens() {

        Garden garden = new Garden();
        garden.setId(1L);
        garden.setName("test name");
        garden.setLocation("test location");

        Garden garden1 = new Garden();
        garden1.setId(2L);
        garden1.setName("test name1");
        garden1.setLocation("test location1");

        List<Garden> gardenList = new ArrayList<>();
        gardenList.add(garden);
        gardenList.add(garden1);

        when(gardenRepository.findAllByIsDeletedFalse()).thenReturn(gardenList);

        List<GardenResponse> responseList = gardenService.getAllGarden();

        assertEquals(gardenList.size(), responseList.size());
        for (int i = 0; i < gardenList.size(); i++) {
            GardenResponse gardenResponse = responseList.get(i);
            Garden gard = gardenList.get(i);

            assertEquals(gard.getId(), gardenResponse.id());
            assertEquals(gard.getName(), gardenResponse.name());
            assertEquals(gard.getLocation(), gardenResponse.location());
        }

        verify(gardenRepository).findAllByIsDeletedFalse();
    }


    @Test
    void getGardenById_Success() {
        Garden garden = new Garden();
        garden.setId(1L);
        garden.setName("test name");

        when(gardenRepository.findById(garden.getId())).thenReturn(Optional.of(garden));

        Garden result = gardenService.getGardenById(garden.getId());

        assertEquals(garden.getId(), result.getId());
        assertEquals(garden.getName(), result.getName());

        verify(gardenRepository).findById(garden.getId());
    }

    @Test
    void getGardenById_NotFound() {

        Long id = 1L;

        when(gardenRepository.findById(id)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            gardenService.getGardenById(id);
        });
        assertEquals("Garden not found!.", exception.getMessage());
        verify(gardenRepository).findById(id);
    }

}