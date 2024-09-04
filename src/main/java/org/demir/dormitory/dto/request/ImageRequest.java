package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageRequest(

        @NotNull(message = "Entity ID must not be null")
        Long entityId,

        @NotBlank(message = "Base64 data must not be blank")
        String base64Data
) {
}
