package org.demir.dormitory.dto.response;

import java.time.LocalDateTime;


public record LeaveResponse(Long id, LocalDateTime startDate, LocalDateTime endDate,
                            boolean isApproved,StudentResponse studentResponse)  {
}