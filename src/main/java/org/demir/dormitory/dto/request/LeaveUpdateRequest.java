package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record LeaveUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotNull(message = "End date cannot be null")
        @Future(message = "End date must be a future date")
        LocalDateTime endDate
) {
}
