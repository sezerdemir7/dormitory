package org.demir.dormitory.dto.request;


public record HallUpdateRequest(Long id, String name, String location,int capacity)   {
}