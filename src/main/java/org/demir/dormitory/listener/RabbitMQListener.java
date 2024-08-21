package org.demir.dormitory.listener;


import org.demir.dormitory.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private final EmailService emailService;

    public RabbitMQListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void listen(String email) {
        emailService.sendEmail(email, "Reservation Approved", "Your reservation has been approved.");
    }

}
