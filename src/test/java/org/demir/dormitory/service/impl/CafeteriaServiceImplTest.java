package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.CafeteriaRequest;
import org.demir.dormitory.dto.request.CafeteriaUpdateRequest;
import org.demir.dormitory.dto.response.CafeteriaResponse;
import org.demir.dormitory.entity.Cafeteria;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.CafeteriaRepository;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CafeteriaServiceImplTest {

    private CafeteriaRepository cafeteriaRepository;
    private CafeteriaServiceImpl cafeteriaService;

    @BeforeEach
    void setUp() {
        cafeteriaRepository= Mockito.mock(CafeteriaRepository.class);
        cafeteriaService=new CafeteriaServiceImpl(cafeteriaRepository);
    }



    @Test
    void saveCafeteria(){
        CafeteriaRequest request =
                new CafeteriaRequest("test","location test");
        Cafeteria toSave=new Cafeteria();
        toSave.setName(request.name());
        toSave.setLocation(request.location());

        Cafeteria savedCafeteria = new Cafeteria();
        savedCafeteria.setId(1L);
        savedCafeteria.setName(toSave.getName());
        savedCafeteria.setLocation(toSave.getLocation());

        when(cafeteriaRepository.save(toSave)).thenReturn(savedCafeteria);

        CafeteriaResponse response = cafeteriaService.saveCafeteria(request);

        assertEquals(savedCafeteria.getId(), response.id());
        assertEquals(savedCafeteria.getName(), response.name());
        assertEquals(savedCafeteria.getLocation(), response.location());

        verify(cafeteriaRepository).save(toSave);


    }
    @Test
    void deleteCafeteria() {

        Long cafeteriaId = 1L;
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(cafeteriaId);

        when(cafeteriaRepository.findById(cafeteriaId)).thenReturn(Optional.of(cafeteria));

        cafeteriaService.deleteCafeteria(cafeteriaId);

        assertTrue(cafeteria.isDeleted());
        verify(cafeteriaRepository).save(cafeteria);


    }

    @Test
    void getCafeteriaByName_Success() {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(1L);
        cafeteria.setName("test name");

        when(cafeteriaRepository.findByName(cafeteria.getName())).thenReturn(Optional.of(cafeteria));

        CafeteriaResponse response = cafeteriaService.getCafeteriaByName("test name");

        assertEquals(cafeteria.getId(), response.id());
        assertEquals(cafeteria.getName(), response.name());

        verify(cafeteriaRepository).findByName(cafeteria.getName());

    }

    @Test
    void getCafeteriaByName_NotFound() {
        String name = "test name";

        when(cafeteriaRepository.findByName(name)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            cafeteriaService.getCafeteriaByName(name);
        });
        assertEquals("Cafeteria not found!.", exception.getMessage());
        verify(cafeteriaRepository).findByName(name);

    }

    @Test
    void whenShouldReturnAllCafeteria() {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(1L);
        cafeteria.setName("test name");
        Cafeteria cafeteria2 = new Cafeteria();
        cafeteria2.setId(2L);
        cafeteria2.setName("test name2");
        List<Cafeteria> cafeteriaList = new ArrayList<>();
        cafeteriaList.add(cafeteria);
        cafeteriaList.add(cafeteria2);

        when(cafeteriaRepository.findAllByIsDeletedFalse()).thenReturn(cafeteriaList);

        List<CafeteriaResponse> responseList= cafeteriaService.getAllCafeteria();

        assertEquals(cafeteriaList.size(), responseList.size());
        for (int i = 0; i < cafeteriaList.size(); i++) {
            CafeteriaResponse response = responseList.get(i);
            Cafeteria caf = cafeteriaList.get(i);

            assertEquals(caf.getId(), response.id());
            assertEquals(caf.getName(), response.name());
            assertEquals(caf.getLocation(), response.location());
        }

        verify(cafeteriaRepository).findAllByIsDeletedFalse();

    }

    @Test
    void updateCafeteria() {
        CafeteriaUpdateRequest request =
                new CafeteriaUpdateRequest(1L,"test name","test location");

        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(request.id());
        cafeteria.setName(request.name());
        cafeteria.setLocation(request.location());

        Cafeteria updated = new Cafeteria();
        updated.setId(cafeteria.getId());
        updated.setName(cafeteria.getName());
        updated.setLocation(cafeteria.getLocation());

        when(cafeteriaRepository.findById(request.id())).thenReturn(Optional.of(cafeteria));
        when(cafeteriaRepository.save(cafeteria)).thenReturn(updated);

        CafeteriaResponse response = cafeteriaService.updateCafeteria(request);
        assertEquals(updated.getId(), response.id());
        assertEquals(updated.getName(), response.name());
        assertEquals(updated.getLocation(), response.location());

        verify(cafeteriaRepository).findById(cafeteria.getId());
        verify(cafeteriaRepository).save(cafeteria);


    }

    @Test
    void getCafeteriaById_Success() {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(1L);
        cafeteria.setName("test name");

        when(cafeteriaRepository.findById(cafeteria.getId())).thenReturn(Optional.of(cafeteria));

        Cafeteria result = cafeteriaService.getCafeteriaById(cafeteria.getId());

        assertEquals(cafeteria.getId(), result.getId());
        assertEquals(cafeteria.getName(), result.getName());

        verify(cafeteriaRepository).findById(cafeteria.getId());

    }

    @Test
    void getCafeteriaById_NotFound() {

        Long id = 1L;

        when(cafeteriaRepository.findById(id)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            cafeteriaService.getOneCafeteriaById(id);
        });
        assertEquals("Cafeteria not found!.", exception.getMessage());
        verify(cafeteriaRepository).findById(id);

    }




    @AfterEach
    void tearDown() {

    }



}