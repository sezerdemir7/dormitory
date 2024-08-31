package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.AuthRequest;

public interface AuthService {
    String generateToken(AuthRequest request);
}
