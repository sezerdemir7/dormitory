package org.demir.dormitory.dto.request;

public record AuthRequest(
        String username,
        String password
) {
}
