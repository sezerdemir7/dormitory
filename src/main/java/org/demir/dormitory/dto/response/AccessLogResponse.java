package org.demir.dormitory.dto.response;


import org.demir.dormitory.entity.enumType.AccessAction;

import java.time.LocalDateTime;

public record AccessLogResponse(Long id, LocalDateTime date, AccessAction action, Long studentId) {
}