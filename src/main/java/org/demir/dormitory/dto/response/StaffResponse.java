package org.demir.dormitory.dto.response;

public record StaffResponse(Long id, String name, String surname, String username, java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> role) {
}
