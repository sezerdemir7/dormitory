package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LessonUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description,

        @Min(value = 0, message = "Current student count must be at least 0")
        int currentStudentCount,

        @Min(value = 1, message = "Max student count must be at least 1")
        int maxStudentCount
) {
}
