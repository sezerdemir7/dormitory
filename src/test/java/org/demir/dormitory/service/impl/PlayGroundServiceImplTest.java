package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.PlayGroundRequest;
import org.demir.dormitory.dto.request.PlayGroundUpdateRequest;
import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.entity.PlayGround;
import org.demir.dormitory.entity.enumType.PlayGroundType;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.PlayGroundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayGroundServiceImplTest {

    private PlayGroundRepository playGroundRepository;
    private PlayGroundServiceImpl playGroundService;

    @BeforeEach
    void setUp() {
        playGroundRepository = Mockito.mock(PlayGroundRepository.class);
        playGroundService = new PlayGroundServiceImpl(playGroundRepository);
    }

    @Test
    void savePlayGround() {
        PlayGroundRequest request = new PlayGroundRequest("Test PlayGround", PlayGroundType.BASKETBALL);
        PlayGround toSave = new PlayGround();
        toSave.setName(request.name());
        toSave.setType(request.type());

        PlayGround savedPlayGround = new PlayGround();
        savedPlayGround.setId(1L);
        savedPlayGround.setName(toSave.getName());
        savedPlayGround.setType(toSave.getType());

        when(playGroundRepository.save(toSave)).thenReturn(savedPlayGround);

        PlayGroundResponse response = playGroundService.savePlayGround(request);

        assertEquals(savedPlayGround.getId(), response.id());
        assertEquals(savedPlayGround.getName(), response.name());
        assertEquals(savedPlayGround.getType(), response.type());

        verify(playGroundRepository).save(toSave);
    }

    @Test
    void deletePlayGround() {
        Long playGroundId = 1L;
        PlayGround playGround = new PlayGround();
        playGround.setId(playGroundId);

        when(playGroundRepository.findById(playGroundId)).thenReturn(Optional.of(playGround));

        playGroundService.deletePlayGround(playGroundId);

        assertTrue(playGround.isDeleted());
        verify(playGroundRepository).save(playGround);
    }

    @Test
    void getPlayGroundByName_Success() {
        PlayGround playGround = new PlayGround();
        playGround.setId(1L);
        playGround.setName("Test PlayGround");

        when(playGroundRepository.findByName(playGround.getName())).thenReturn(Optional.of(playGround));

        PlayGroundResponse response = playGroundService.getPlayGroundByName("Test PlayGround");

        assertEquals(playGround.getId(), response.id());
        assertEquals(playGround.getName(), response.name());

        verify(playGroundRepository).findByName(playGround.getName());
    }

    @Test
    void getPlayGroundByName_NotFound() {
        String name = "Test PlayGround";

        when(playGroundRepository.findByName(name)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            playGroundService.getPlayGroundByName(name);
        });

        assertEquals("PlayGround not found!", exception.getMessage());
        verify(playGroundRepository).findByName(name);
    }

    @Test
    void getAllPlayGround() {
        PlayGround playGround1 = new PlayGround();
        playGround1.setId(1L);
        playGround1.setName("PlayGround 1");

        PlayGround playGround2 = new PlayGround();
        playGround2.setId(2L);
        playGround2.setName("PlayGround 2");

        List<PlayGround> playGroundList = new ArrayList<>();
        playGroundList.add(playGround1);
        playGroundList.add(playGround2);

        when(playGroundRepository.findAllByIsDeletedFalse()).thenReturn(playGroundList);

        List<PlayGroundResponse> responseList = playGroundService.getAllPlayGround();

        assertEquals(playGroundList.size(), responseList.size());
        for (int i = 0; i < playGroundList.size(); i++) {
            PlayGroundResponse response = responseList.get(i);
            PlayGround playGround = playGroundList.get(i);

            assertEquals(playGround.getId(), response.id());
            assertEquals(playGround.getName(), response.name());
            assertEquals(playGround.getType(), response.type());
        }

        verify(playGroundRepository).findAllByIsDeletedFalse();
    }

    @Test
    void updatePlayGround() {
        PlayGroundUpdateRequest request = new PlayGroundUpdateRequest(1L, "Updated PlayGround", PlayGroundType.FOOTBALL);
        PlayGround playGround = new PlayGround();
        playGround.setId(request.id());
        playGround.setName(request.name());
        playGround.setType(request.type());

        when(playGroundRepository.findById(request.id())).thenReturn(Optional.of(playGround));
        when(playGroundRepository.save(playGround)).thenReturn(playGround);

        PlayGroundResponse response = playGroundService.updatePlayGround(request);

        assertEquals(playGround.getId(), response.id());
        assertEquals(playGround.getName(), response.name());
        assertEquals(playGround.getType(), response.type());

        verify(playGroundRepository).findById(playGround.getId());
        verify(playGroundRepository).save(playGround);
    }

    @Test
    void getPlayGroundById_Success() {
        PlayGround playGround = new PlayGround();
        playGround.setId(1L);
        playGround.setName("Test PlayGround");

        when(playGroundRepository.findById(playGround.getId())).thenReturn(Optional.of(playGround));

        PlayGround result = playGroundService.getPlayGroundById(playGround.getId());

        assertEquals(playGround.getId(), result.getId());
        assertEquals(playGround.getName(), result.getName());

        verify(playGroundRepository).findById(playGround.getId());
    }

    @Test
    void getPlayGroundById_NotFound() {
        Long id = 1L;

        when(playGroundRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            playGroundService.getOnePlayGroundById(id);
        });

        assertEquals("PlayGround not found!", exception.getMessage());
        verify(playGroundRepository).findById(id);
    }
}
