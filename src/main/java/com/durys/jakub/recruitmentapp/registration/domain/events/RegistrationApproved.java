package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record RegistrationApproved(UUID id, Instant at, UUID registrationId) implements DomainEvent {

    public RegistrationApproved(UUID registrationId) {
        this(UUID.randomUUID(), Instant.now(), registrationId);
    }
}
