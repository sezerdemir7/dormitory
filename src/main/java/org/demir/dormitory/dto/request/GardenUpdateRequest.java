package org.demir.dormitory.dto.request;


public record GardenUpdateRequest(Long id, String name, String location, boolean isAvailable)  {
}