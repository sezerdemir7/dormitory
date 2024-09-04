package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(max = 50, message = "Surname cannot exceed 50 characters")
        String surname,

        @NotNull(message = "Room ID cannot be null")
        Long roomId
) {
}
