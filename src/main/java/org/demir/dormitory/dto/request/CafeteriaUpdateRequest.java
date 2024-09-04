package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CafeteriaUpdateRequest(
        @NotNull
        Long id,

        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Location must not be blank")
        String location
) {
}
