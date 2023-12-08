package com.durys.jakub.recruitmentapp.commons.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbimqNotificationClient implements NotificationClient {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.notification}")
    private String notificationQueue;

    @Override
    public void publish(Notification notification) {
        rabbitTemplate.convertAndSend(notificationQueue, "", notification);
    }
}
