package com.transaction.management.kafka.consumer;

import com.transaction.management.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationConsumer {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "${kafka.transaction.topic}", groupId = "email-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(NotificationResponse notification) {
        System.out.println("📩 Received Kafka notification: " + notification);

        if ("SUCCESSFUL".equalsIgnoreCase(notification.getStatus())) {
            sendSuccessEmail(notification);
        } else {
            sendFailureEmail(notification);
        }
    }

    private void sendSuccessEmail(NotificationResponse notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getEmail());
        message.setSubject("Transaction Successful");
        message.setText("Dear User,\n\nYour transaction was successful.\n\nDetails:\n" + notification.getMessage());
        mailSender.send(message);
        System.out.println("Email sent to: " + notification.getEmail());
    }

    private void sendFailureEmail(NotificationResponse notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getEmail());
        message.setSubject("Transaction Failed");
        message.setText("Dear User,\n\nYour transaction has failed.\n\nDetails:\n" + notification.getMessage());
        mailSender.send(message);
        System.out.println("Failure email sent to: " + notification.getEmail());
    }
}
