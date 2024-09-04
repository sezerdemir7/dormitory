package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RoomUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @Min(value = 0, message = "Current occupancy must be at least 0")
        int currentOccupancy
) {
}
