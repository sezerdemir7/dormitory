package org.demir.dormitory.service;

public interface VerificationCodeService {
    String generateVerificationCode(String userId);

    boolean verifyCode(String userId, String code);
}
