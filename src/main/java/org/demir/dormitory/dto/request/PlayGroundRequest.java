package org.demir.dormitory.dto.request;

import org.demir.dormitory.entity.enumType.PlayGroundType;

public record PlayGroundRequest(String name, PlayGroundType type)  {
}