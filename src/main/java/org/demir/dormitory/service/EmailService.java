package org.demir.dormitory.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
