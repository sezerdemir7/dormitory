package org.demir.dormitory.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link org.demir.dormitory.entity.ContactInfo}
 */
public record ContactInfoResponse(Long id, String email, String phoneNumber, String address) implements Serializable {
}