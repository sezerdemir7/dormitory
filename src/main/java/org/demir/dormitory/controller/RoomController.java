package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.RoomRequest;
import org.demir.dormitory.dto.request.RoomUpdateRequest;
import org.demir.dormitory.dto.response.RoomResponse;
import org.demir.dormitory.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/oneRoom")
    public ApiResponse<RoomResponse> getRoomById(@RequestParam Long id) {
        return new ApiResponse<>("Room retrieved successfully.", roomService.getOneRoomById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<RoomResponse> saveRoom(@RequestBody RoomRequest request) {
        return new ApiResponse<>("Room saved successfully.", roomService.saveRoom(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return new ApiResponse<>("Rooms retrieved successfully.", roomService.getAllRoom(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<RoomResponse> updateRoom(@RequestBody @Valid RoomUpdateRequest request) {
        return new ApiResponse<>("Room updated successfully.", roomService.updateRoom(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteRoom(@RequestParam Long id) {
        roomService.deleteRoom(id);
        return new ApiResponse<>("Room deleted successfully.", null, HttpStatus.OK);
    }
}
