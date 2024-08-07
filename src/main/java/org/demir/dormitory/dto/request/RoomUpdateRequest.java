package org.demir.dormitory.dto.request;


public record RoomUpdateRequest(Long id, int currentOccupancy) {
}