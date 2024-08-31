package org.demir.dormitory.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.demir.dormitory.dto.request.AuthRequest;
import org.demir.dormitory.service.AuthService;
import org.demir.dormitory.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public String generateToken(AuthRequest request){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.username());
        }

        log.info("invalid username" + request.username());
        throw new UsernameNotFoundException("invalid username {} "+request.username());
    }
}