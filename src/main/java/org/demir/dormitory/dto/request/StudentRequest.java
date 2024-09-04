package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StudentRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Surname must not be blank")
        String surname,

        @Positive(message = "Room ID must be a positive number")
        @NotNull(message = "Room ID must not be null")
        Long roomId
) {
}
