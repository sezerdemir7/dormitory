package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.RoomRequest;
import org.demir.dormitory.dto.request.RoomUpdateRequest;
import org.demir.dormitory.dto.response.RoomResponse;
import org.demir.dormitory.entity.Room;

import java.util.List;

public interface RoomService {


    RoomResponse saveRoom(RoomRequest roomRequest);
    void deleteRoom(Long roomId);
    List<RoomResponse> getAllRoom();
    RoomResponse getOneRoomById(Long roomId);
    RoomResponse updateRoom(RoomUpdateRequest request);
    Room getRoomById(Long roomId);
    Object getRoomWithCapacityCheck(Long roomId);
    Room saveRoom(Room room);
}
