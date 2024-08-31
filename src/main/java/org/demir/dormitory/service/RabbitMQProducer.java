package org.demir.dormitory.service;

public interface RabbitMQProducer {


     void sendMailAddressToQueue(String mail);

    void sendVerificationCodeToQueue(String email, String verificationCode);
}
