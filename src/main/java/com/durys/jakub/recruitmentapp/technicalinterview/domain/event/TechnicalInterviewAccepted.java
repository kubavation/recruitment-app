package com.durys.jakub.recruitmentapp.technicalinterview.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewId;

import java.time.Instant;
import java.util.UUID;

public record TechnicalInterviewAccepted(UUID id, Instant at, TechnicalInterviewId technicalInterviewId) implements DomainEvent {

    public TechnicalInterviewAccepted(TechnicalInterviewId technicalInterviewId) {
        this(UUID.randomUUID(), Instant.now(), technicalInterviewId);
    }

}
