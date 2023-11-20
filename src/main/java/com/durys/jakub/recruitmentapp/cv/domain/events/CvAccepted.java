package com.durys.jakub.recruitmentapp.cv.domain.events;

import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record CvAccepted(UUID id, Instant at, CvId cvId, RegistrationId registrationId) implements DomainEvent {

    public CvAccepted(CvId cvId, RegistrationId registrationId) {
        this(UUID.randomUUID(), Instant.now(), cvId, registrationId);
    }
}
