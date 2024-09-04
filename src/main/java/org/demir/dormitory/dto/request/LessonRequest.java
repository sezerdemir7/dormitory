package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record LessonRequest(
        @NotBlank(message = "Lesson name must not be blank")
        String name,

        @NotBlank(message = "Description must not be blank")
        String description,

        @PositiveOrZero(message = "Current student count must be zero or positive")
        int currentStudentCount,

        @Min(value = 1, message = "Maximum student count must be at least 1")
        int maxStudentCount,

        @NotNull(message = "Teacher ID must not be null")
        Long teacherId
) {
}