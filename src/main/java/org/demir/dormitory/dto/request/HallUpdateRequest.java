package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HallUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Location cannot be blank")
        @Size(max = 255, message = "Location cannot exceed 255 characters")
        String location,

        @Min(value = 1, message = "Capacity must be at least 1")
        int capacity
) {
}
