package org.demir.dormitory.dto.request;




public record HallRequest(String name, String location, Long employeeId, int capacity)   {
}