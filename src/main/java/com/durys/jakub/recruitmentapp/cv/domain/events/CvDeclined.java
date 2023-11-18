package com.durys.jakub.recruitmentapp.cv.domain.events;

import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;

import java.time.Instant;
import java.util.UUID;

public record CvDeclined(UUID id, Instant at, CvId cvId, RegistrationId registrationId) implements DomainEvent {

    public CvDeclined(CvId cvId, RegistrationId registrationId) {
        this(UUID.randomUUID(), Instant.now(), cvId, registrationId);
    }
}
