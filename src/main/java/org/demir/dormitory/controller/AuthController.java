package org.demir.dormitory.controller;

import org.demir.dormitory.service.VerificationCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final VerificationCodeService verificationCodeService;

    public AuthController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId) {
        // Kullanıcı kimlik doğrulama işlemleri

        String code = verificationCodeService.generateVerificationCode(userId);
        System.out.println("Onaylama kodu: " + code);

        return "Onaylama kodu gönderildi";
    }

    @PostMapping("/verify")
    public String verify(@RequestParam String userId, @RequestParam String code) {
        boolean isVerified = verificationCodeService.verifyCode(userId, code);
        if (isVerified) {
            return "Giriş başarılı";
        } else {
            return "Geçersiz onaylama kodu";
        }
    }
}

