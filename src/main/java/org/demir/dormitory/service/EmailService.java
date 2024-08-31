package org.demir.dormitory.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendVerificationCode(String to, String code);
}
