package org.demir.dormitory.dto.request;

public record ContactInfoUpdateRequest(Long id, String email, String phoneNumber, String address)  {
}