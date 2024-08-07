package org.demir.dormitory.dto.response;

public record HallResponse(Long id, String name, String location, boolean isAvailable,
                           int capacity,Long employeeId)  {
}