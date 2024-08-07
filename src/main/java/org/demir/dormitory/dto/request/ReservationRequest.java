package org.demir.dormitory.dto.request;


import java.time.LocalDateTime;

/**
 * DTO for {@link org.demir.dormitory.entity.Reservation}
 */
public record ReservationRequest(LocalDateTime startDate,
                                 LocalDateTime endDate, Long playGroundId,Long studentId)  {
}