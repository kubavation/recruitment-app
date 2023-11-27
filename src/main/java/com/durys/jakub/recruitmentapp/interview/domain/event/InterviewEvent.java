package com.durys.jakub.recruitmentapp.interview.domain.event;

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

    record InterviewCompleted(UUID id, Instant at, UUID interviewId, String opinion, boolean acceptation) implements InterviewEvent {

        public InterviewCompleted(UUID interviewId, String opinion, boolean acceptation) {
            this(UUID.randomUUID(), Instant.now(), interviewId, opinion, acceptation);
        }
    }

    record InvitationAccepted(UUID id, Instant at, UUID interviewId) implements InterviewEvent {

        public InvitationAccepted(UUID interviewId) {
            this(UUID.randomUUID(), Instant.now(), interviewId);
        }
    }

    record InvitationDeclined(UUID id, Instant at, UUID interviewId) implements InterviewEvent {

        public InvitationDeclined(UUID interviewId) {
            this(UUID.randomUUID(), Instant.now(), interviewId);
        }
    }
}
