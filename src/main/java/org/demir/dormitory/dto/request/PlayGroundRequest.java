package org.demir.dormitory.dto.request;

import org.demir.dormitory.common.PlayGroundType;

import java.io.Serializable;

public record PlayGroundRequest(String name, PlayGroundType type)  {
}