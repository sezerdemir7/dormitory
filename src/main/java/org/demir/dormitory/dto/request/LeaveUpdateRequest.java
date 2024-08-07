package org.demir.dormitory.dto.request;


import java.time.LocalDateTime;

public record LeaveUpdateRequest(Long id, LocalDateTime endDate)  {
}