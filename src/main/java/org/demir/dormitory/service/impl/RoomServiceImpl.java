package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.RoomRequest;
import org.demir.dormitory.dto.request.RoomUpdateRequest;
import org.demir.dormitory.dto.response.RoomResponse;
import org.demir.dormitory.entity.Room;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.RoomRepository;
import org.demir.dormitory.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomResponse saveRoom(RoomRequest roomRequest) {
        Room tosave=mapToRoom(roomRequest);
        Room room=roomRepository.save(tosave);
        return mapToResponse(room);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(()->new NotFoundException("Room not found!"));
        room.setDeleted(true);
        roomRepository.save(room);
    }

    @Override
    public List<RoomResponse> getAllRoom() {
        List<Room> roomList=roomRepository.findAll();
        return mapToReponseList(roomList);
    }

    @Override
    public RoomResponse getOneRoomById(Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(()->
                new NotFoundException("Room not found exception"));
        return mapToResponse(room);
    }

    @Override
    public RoomResponse updateRoom(RoomUpdateRequest request) {
        Room toUpdate= findRoomById(request.id());
        toUpdate.setCurrentOccupancy(request.currentOccupancy());
        Room room=roomRepository.save(toUpdate);
        return mapToResponse(room);
    }

    private Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() ->
                new NotFoundException("Room not found exception"));
    }

    @Override
    public Room getRoomById(Long roomId) {
        return findRoomById(roomId);
    }

    @Override
    public Object getRoomWithCapacityCheck(Long roomId) {
        Room room=findRoomById(roomId);
        if(room.getCapacity()==room.getCurrentOccupancy()){
            return new BadRequestException("Room capacity is full");
        }
        return room;
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    private RoomResponse mapToResponse(Room room){
        RoomResponse response=
                new RoomResponse(room.getId(),
                        room.getCapacity(),
                        room.getCurrentOccupancy());
        return response;
    }

    private Room mapToRoom(RoomRequest request){
        Room room=new Room();
        room.setCapacity(request.capacity());
        return room;
    }

    private List<RoomResponse> mapToReponseList(List<Room> roomList){
        List<RoomResponse> responseList=
                roomList.stream().map(this::mapToResponse).collect(Collectors.toList());
        return responseList;
    }
}
