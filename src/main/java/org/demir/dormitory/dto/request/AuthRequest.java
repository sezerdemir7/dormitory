package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Username must not be blank")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters")
        String password
) {
}
