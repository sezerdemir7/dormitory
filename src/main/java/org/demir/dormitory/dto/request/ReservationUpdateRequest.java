package org.demir.dormitory.dto.request;

import java.time.LocalDateTime;

public record ReservationUpdateRequest(Long id, LocalDateTime startDate,
                                       LocalDateTime endDate)  {
}