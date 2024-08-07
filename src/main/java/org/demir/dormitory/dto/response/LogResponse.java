package org.demir.dormitory.dto.response;

import java.time.LocalDateTime;

public record LogResponse(Long id, LocalDateTime createdDate, String entityName,
                          Long entityId,String action)  {
}