package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GardenUpdateRequest(
        @NotBlank(message = "ID cannot be null or blank")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Location cannot be blank")
        @Size(max = 255, message = "Location cannot exceed 255 characters")
        String location,

        boolean isAvailable
) {
}
