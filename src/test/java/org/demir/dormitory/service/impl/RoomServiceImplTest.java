package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.RoomRequest;
import org.demir.dormitory.dto.request.RoomUpdateRequest;
import org.demir.dormitory.dto.response.RoomResponse;
import org.demir.dormitory.entity.Room;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    private RoomRepository roomRepository;
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        roomRepository = Mockito.mock(RoomRepository.class);
        roomService = new RoomServiceImpl(roomRepository);
    }

    @Test
    void saveRoom() {
        RoomRequest request = new RoomRequest(10);

        Room toSave = new Room();
        toSave.setCapacity(request.capacity());

        Room savedRoom = new Room();
        savedRoom.setId(1L);
        savedRoom.setCapacity(request.capacity());
        savedRoom.setCurrentOccupancy(0);

        when(roomRepository.save(any(Room.class))).thenReturn(savedRoom);

        RoomResponse response = roomService.saveRoom(request);

        assertEquals(savedRoom.getId(), response.id());
        assertEquals(savedRoom.getCapacity(), response.capacity());
        assertEquals(savedRoom.getCurrentOccupancy(), response.currentOccupancy());

        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void deleteRoom() {
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        roomService.deleteRoom(roomId);

        assertTrue(room.isDeleted());
        verify(roomRepository).save(room);
    }

    @Test
    void getAllRoom() {
        Room room1 = new Room();
        room1.setId(1L);
        room1.setCapacity(10);
        room1.setCurrentOccupancy(5);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setCapacity(15);
        room2.setCurrentOccupancy(7);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

        when(roomRepository.findAllByIsDeletedFalse()).thenReturn(roomList);

        List<RoomResponse> responseList = roomService.getAllRoom();

        assertEquals(roomList.size(), responseList.size());
        for (int i = 0; i < roomList.size(); i++) {
            RoomResponse response = responseList.get(i);
            Room room = roomList.get(i);

            assertEquals(room.getId(), response.id());
            assertEquals(room.getCapacity(), response.capacity());
            assertEquals(room.getCurrentOccupancy(), response.currentOccupancy());
        }

        verify(roomRepository).findAllByIsDeletedFalse();
    }

    @Test
    void getOneRoomById_Success() {
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);
        room.setCapacity(10);
        room.setCurrentOccupancy(5);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        RoomResponse response = roomService.getOneRoomById(roomId);

        assertEquals(room.getId(), response.id());
        assertEquals(room.getCapacity(), response.capacity());
        assertEquals(room.getCurrentOccupancy(), response.currentOccupancy());

        verify(roomRepository).findById(roomId);
    }

    @Test
    void getOneRoomById_NotFound() {
        Long roomId = 1L;

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            roomService.getOneRoomById(roomId);
        });

        assertEquals("Room not found exception", exception.getMessage());
        verify(roomRepository).findById(roomId);
    }

    @Test
    void updateRoom_Success() {
        RoomUpdateRequest request = new RoomUpdateRequest(1L, 8);
        Room room = new Room();
        room.setId(request.id());
        room.setCapacity(10);
        room.setCurrentOccupancy(5);

        when(roomRepository.findById(request.id())).thenReturn(Optional.of(room));
        when(roomRepository.save(room)).thenReturn(room);

        RoomResponse response = roomService.updateRoom(request);

        assertEquals(room.getId(), response.id());
        assertEquals(room.getCurrentOccupancy(), response.currentOccupancy());

        verify(roomRepository).findById(request.id());
        verify(roomRepository).save(room);
    }

    @Test
    void updateRoom_NotFound() {
        RoomUpdateRequest request = new RoomUpdateRequest(1L, 8);

        when(roomRepository.findById(request.id())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            roomService.updateRoom(request);
        });

        assertEquals("Room not found exception", exception.getMessage());
        verify(roomRepository).findById(request.id());
    }

    @Test
    void getRoomWithCapacityCheck_Success() {
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);
        room.setCapacity(10);
        room.setCurrentOccupancy(5);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        Room result = (Room) roomService.getRoomWithCapacityCheck(roomId);

        assertEquals(room.getId(), result.getId());
        assertEquals(room.getCapacity(), result.getCapacity());
        assertEquals(room.getCurrentOccupancy(), result.getCurrentOccupancy());

        verify(roomRepository).findById(roomId);
    }


}
