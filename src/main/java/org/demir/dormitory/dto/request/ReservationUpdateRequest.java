package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservationUpdateRequest(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotNull(message = "Start date cannot be null")
        LocalDateTime startDate,

        @NotNull(message = "End date cannot be null")
        LocalDateTime endDate
) {
}
