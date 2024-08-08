package org.demir.dormitory.service;

public interface RabbitMQProducer {


    public void sendMailAddressToQueue(String mail);

}
