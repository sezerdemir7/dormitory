package org.demir.dormitory.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link org.demir.dormitory.entity.ContactInfo}
 */
public record ContactInfoResponsee(Long id, String email, String phoneNumber, String address) implements Serializable {
}