package com.durys.jakub.recruitmentapp.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
class SpringEventEmitter implements EventEmitter {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void emit(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
