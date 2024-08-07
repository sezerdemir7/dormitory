package org.demir.dormitory.dto.request;

public record StudentUpdateRequest(Long id,String name,String surname, Long roomId)  {
}