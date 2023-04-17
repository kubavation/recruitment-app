package com.durys.jakub.recruitmentapp.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitmqMailClient implements MailClient {

    private final RabbitTemplate rabbitTemplate;

    @Value("${q.mail-queue}")
    private String mailQueue;

    @Override
    public void send(Mail mail) {
        rabbitTemplate.convertAndSend(mailQueue, "", mail);
    }
}
