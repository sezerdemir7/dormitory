package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HallRequest(
        @NotBlank(message = "Name must not be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Location must not be blank")
        @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
        String location,

        @NotNull(message = "Employee ID must not be null")
        Long employeeId,

        @Min(value = 1, message = "Capacity must be at least 1")
        int capacity
) {
}
