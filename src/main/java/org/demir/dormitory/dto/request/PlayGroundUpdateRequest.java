package org.demir.dormitory.dto.request;


import org.demir.dormitory.common.PlayGroundType;

public record PlayGroundUpdateRequest(Long id, String name, PlayGroundType type)  {
}