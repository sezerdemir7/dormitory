package org.demir.dormitory.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String userName);
    Boolean validateToken(String token, UserDetails userDetails);
    String extractUser(String token);
}
