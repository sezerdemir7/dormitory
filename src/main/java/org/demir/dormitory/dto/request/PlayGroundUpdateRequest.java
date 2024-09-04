package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.demir.dormitory.entity.enumType.PlayGroundType;

public record PlayGroundUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "PlayGroundType cannot be null")
        PlayGroundType type
) {
}
