package com.durys.jakub.recruitmentapp.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventsConfiguration {

    @Bean
    EventEmitter eventEmitter(ApplicationEventPublisher applicationEventPublisher) {
        return new SpringEventEmitter(applicationEventPublisher);
    }
}
