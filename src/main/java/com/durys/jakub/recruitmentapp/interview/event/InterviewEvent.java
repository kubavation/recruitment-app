package com.durys.jakub.recruitmentapp.interview.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface InterviewEvent extends DomainEvent {

    record InterviewInitialized(UUID id, Instant at, UUID interviewId, UUID registrationId, UUID tenantId) implements InterviewEvent {

        public InterviewInitialized(UUID interviewId, UUID registrationId, UUID tenantId) {
            this(UUID.randomUUID(), Instant.now(), interviewId, registrationId, tenantId);
        }
    }

    record ReviewerAssigned(UUID id, Instant at, UUID interviewId, UUID reviewerId, LocalDateTime interviewAt) implements InterviewEvent {

        public ReviewerAssigned(UUID interviewId, UUID reviewerId, LocalDateTime interviewAt) {
            this(UUID.randomUUID(), Instant.now(), interviewId, reviewerId, interviewAt);
        }
    }
}
