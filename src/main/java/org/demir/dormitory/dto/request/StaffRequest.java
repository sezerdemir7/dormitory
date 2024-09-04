package org.demir.dormitory.dto.request;

import org.demir.dormitory.entity.enumType.StaffRole;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.demir.dormitory.entity.enumType.StaffRole;

import java.util.Set;

public record StaffRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 30, message = "Name cannot exceed 30 characters")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(max = 30, message = "Surname cannot exceed 30 characters")
        String surname,

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 5, message = "Password must be at least 5 characters long")
        String password,

        @NotEmpty(message = "Authorities must not be empty")
        Set<StaffRole> authorities
) {
}
