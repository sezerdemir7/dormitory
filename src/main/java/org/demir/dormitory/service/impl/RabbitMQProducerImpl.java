package org.demir.dormitory.service.impl;

import org.demir.dormitory.service.RabbitMQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerImpl implements RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.verification.routing.key}")
    private String verificationRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMailAddressToQueue(String mail){
        rabbitTemplate.convertAndSend(exchange, routingKey, mail);
    }

    public void sendVerificationCodeToQueue(String email, String verificationCode){
        String message = email + "," + verificationCode;
        rabbitTemplate.convertAndSend(exchange, verificationRoutingKey, message);
    }
}
