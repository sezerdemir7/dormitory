package org.demir.dormitory.dto.response;

import org.demir.dormitory.common.PlayGroundType;

public record PlayGroundResponse(Long id, String name, PlayGroundType type, boolean isAvailable){
}