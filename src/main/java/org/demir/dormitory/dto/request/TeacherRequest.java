package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TeacherRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Surname must not be blank")
        String surname
) {
}
