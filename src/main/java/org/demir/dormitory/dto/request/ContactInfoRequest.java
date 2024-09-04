package org.demir.dormitory.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactInfoRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number cannot be blank")
        @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
        String phoneNumber,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String address
) {
}