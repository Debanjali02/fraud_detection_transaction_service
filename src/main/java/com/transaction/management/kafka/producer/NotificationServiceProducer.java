package com.transaction.management.kafka.producer;

import com.transaction.management.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@EnableKafka
@RequiredArgsConstructor
public class NotificationServiceProducer {

    private final KafkaTemplate<String, NotificationResponse> kafkaNotificationProducerTemplate;

    @Async
    public void send(String topic, String key, NotificationResponse message) {
        try {
            kafkaNotificationProducerTemplate.send(topic, key, message).whenComplete((result, exception) -> {
                if (exception == null) {
                    System.out.println("Kafka message sent successfully: " + message);
                } else {
                    System.out.println("Failed to send message: " + exception.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Exception occurred while sending message: " + e.getMessage());
        }
    }
}
