package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.Cv;
import com.durys.jakub.recruitmentapp.registration.domain.OfferId;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record RegistrationAccepted(UUID id, Instant at, RegistrationId registrationId, OfferId offerId, Cv cv) implements DomainEvent {

    public RegistrationAccepted(RegistrationId registrationId, OfferId offerId, Cv cv) {
        this(UUID.randomUUID(), Instant.now(), registrationId, offerId, cv);
    }

}
