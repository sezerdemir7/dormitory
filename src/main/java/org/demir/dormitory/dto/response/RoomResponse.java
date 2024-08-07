package org.demir.dormitory.dto.response;


public record RoomResponse(Long id, int capacity, int currentOccupancy)  {
}