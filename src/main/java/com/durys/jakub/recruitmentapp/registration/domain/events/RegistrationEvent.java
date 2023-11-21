package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public sealed interface RegistrationEvent extends DomainEvent {


    record RegistrationApproved(UUID id, Instant at, UUID registrationId) implements RegistrationEvent {

        public RegistrationApproved(UUID registrationId) {
            this(UUID.randomUUID(), Instant.now(), registrationId);
        }
    }

    record RegistrationRejected(UUID id, Instant at, UUID registrationId, String reason) implements RegistrationEvent {

        public RegistrationRejected(UUID registrationId, String reason) {
            this(UUID.randomUUID(), Instant.now(), registrationId, reason);
        }
    }


}
