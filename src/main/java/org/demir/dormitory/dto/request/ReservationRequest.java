package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record ReservationRequest(
        @Future(message = "Start date must be in the future")
        @NotNull(message = "Start date must not be null")
        LocalDateTime startDate,

        @Future(message = "End date must be in the future")
        @NotNull(message = "End date must not be null")
        LocalDateTime endDate,

        @Positive(message = "Playground ID must be a positive number")
        @NotNull(message = "Playground ID must not be null")
        Long playGroundId,

        @Positive(message = "Student ID must be a positive number")
        @NotNull(message = "Student ID must not be null")
        Long studentId
) {
}
