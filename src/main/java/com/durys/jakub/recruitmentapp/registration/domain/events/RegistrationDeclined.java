package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.ApplicantInformation;

import java.time.Instant;
import java.util.UUID;

public record RegistrationDeclined(UUID id, Instant at, RegistrationId registrationId, OfferId offerId,
                                   ApplicantInformation applicantInformation, String reason) implements DomainEvent {

    public RegistrationDeclined(RegistrationId registrationId, OfferId offerId,
                                ApplicantInformation applicantInformation, String reason) {
        this(UUID.randomUUID(), Instant.now(), registrationId, offerId, applicantInformation, reason);
    }
}
