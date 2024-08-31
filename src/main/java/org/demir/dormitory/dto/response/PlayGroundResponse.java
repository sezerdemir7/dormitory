package org.demir.dormitory.dto.response;

import org.demir.dormitory.entity.enumType.PlayGroundType;

public record PlayGroundResponse(Long id, String name, PlayGroundType type, boolean isAvailable){
}