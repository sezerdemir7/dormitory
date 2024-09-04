package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.demir.dormitory.entity.enumType.PlayGroundType;

public record PlayGroundRequest(
        @NotBlank(message = "Playground name must not be blank")
        String name,

        @NotNull(message = "Playground type must not be null")
        PlayGroundType type
) {
}
