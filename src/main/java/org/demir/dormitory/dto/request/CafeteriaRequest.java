package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CafeteriaRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Location must not be blank")
        String location
) {
}