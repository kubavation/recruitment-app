package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent.*;

@Component
@Slf4j
class RegistrationEventHandler implements EventHandler<RegistrationEvent> {

    @Override
    public void handle(RegistrationEvent registrationEvent) {
        switch (registrationEvent) {
            case RegistrationApproved approved -> handle(approved);
            case RegistrationRejected rejected -> handle(rejected);
            case RegistrationSubmitted submitted -> handle(submitted);
            default -> log.warn("Unsupported event {}", registrationEvent);
        }
    }

    void handle(RegistrationSubmitted event) {

    }

    void handle(RegistrationApproved event) {

    }

    void handle(RegistrationRejected event) {

    }

}
