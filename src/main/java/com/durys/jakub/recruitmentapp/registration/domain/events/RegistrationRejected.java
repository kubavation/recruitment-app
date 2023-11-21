package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record RegistrationRejected(UUID id, Instant at, UUID registrationId, String reason) implements DomainEvent {

    public RegistrationRejected(UUID registrationId, String reason) {
        this(UUID.randomUUID(), Instant.now(), registrationId, reason);
    }
}

