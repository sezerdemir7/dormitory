package org.demir.dormitory.dto.request;


import java.time.LocalDateTime;

public record LeaveRequest(LocalDateTime startDate, LocalDateTime endDate,Long studentId)  {
}