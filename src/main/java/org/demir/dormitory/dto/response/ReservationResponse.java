package org.demir.dormitory.dto.response;

import java.time.LocalDateTime;


public record ReservationResponse(Long id, LocalDateTime reservationDate, LocalDateTime startDate,
                                  LocalDateTime endDate, boolean isApproved, PlayGroundResponse playGroundResponse, StudentResponse studentResponse
                                  ) {
}