package org.demir.dormitory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.demir.dormitory.entity.enumType.StaffRole;

import java.util.Set;

public record StaffUpdateRequest(
        @NotBlank(message = "ID cannot be blank")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(max = 50, message = "Surname cannot exceed 50 characters")
        String surname,

        @NotBlank(message = "Username cannot be blank")
        @Size(max = 50, message = "Username cannot exceed 50 characters")
        String username,

        Set<StaffRole> authorities
) {
}
