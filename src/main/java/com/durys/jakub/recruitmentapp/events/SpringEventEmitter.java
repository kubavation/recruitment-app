package com.durys.jakub.recruitmentapp.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
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
