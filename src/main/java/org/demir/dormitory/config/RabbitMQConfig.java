package org.demir.dormitory.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String demirQueueName;

    @Value("${rabbitmq.verification.queue.name}")
    private String verificationQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.verification.routing.key}")
    private String verificationRoutingKey;

    @Bean
    public Queue demirQueue() {
        return new Queue(demirQueueName, true);
    }

    @Bean
    public Queue verificationQueue() {
        return new Queue(verificationQueueName, true);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingDemirQueue() {
        return BindingBuilder.bind(demirQueue())
                .to(exchange())
                .with(routingKey).noargs();
    }

    @Bean
    public Binding bindingVerificationQueue() {
        return BindingBuilder.bind(verificationQueue())
                .to(exchange())
                .with(verificationRoutingKey).noargs();
    }
}
