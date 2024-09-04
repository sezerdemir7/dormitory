package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record LeaveRequest(
        @NotNull(message = "Start date must not be null")
        @Future(message = "Start date must be in the future")
        LocalDateTime startDate,

        @NotNull(message = "End date must not be null")
        @Future(message = "End date must be in the future")
        LocalDateTime endDate,

        @NotNull(message = "Student ID must not be null")
        @Positive(message = "Student ID must be a positive number")
        Long studentId
) {
    public LeaveRequest {

        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }
}