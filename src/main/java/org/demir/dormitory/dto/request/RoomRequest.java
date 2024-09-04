package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;

public record RoomRequest(
        @Min(value = 1, message = "Capacity must be at least 1")
        int capacity
) {
}