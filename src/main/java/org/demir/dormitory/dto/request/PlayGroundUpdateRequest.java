package org.demir.dormitory.dto.request;


import org.demir.dormitory.entity.enumType.PlayGroundType;

public record PlayGroundUpdateRequest(Long id, String name, PlayGroundType type)  {
}