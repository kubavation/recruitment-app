package com.durys.jakub.recruitmentapp.interview.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public sealed interface InterviewEvent extends DomainEvent {

    record InterviewInitialized(UUID id, Instant at, UUID interviewId,
                                UUID registrationId, UUID tenantId, String identifier) implements InterviewEvent {

        public InterviewInitialized(UUID interviewId, UUID registrationId, UUID tenantId, String identifier) {
            this(UUID.randomUUID(), Instant.now(), interviewId, registrationId, tenantId, identifier);
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

    record InvitationAccepted(UUID id, Instant at, UUID interviewId, ReviewerId reviewerId, LocalDateTime term) implements InterviewEvent {

        public InvitationAccepted(UUID interviewId, ReviewerId reviewerId, LocalDateTime term) {
            this(UUID.randomUUID(), Instant.now(), interviewId, reviewerId, term);
        }
    }

    record InvitationSent(UUID id, Instant at, UUID interviewId, ReviewerId reviewerId, List<AvailableTerm> availableTerms) implements InterviewEvent {

        public InvitationSent(UUID interviewId, ReviewerId reviewerId, List<AvailableTerm> availableTerms) {
            this(UUID.randomUUID(), Instant.now(), interviewId, reviewerId, availableTerms);
        }
    }

    record InvitationDeclined(UUID id, Instant at, UUID interviewId) implements InterviewEvent {

        public InvitationDeclined(UUID interviewId) {
            this(UUID.randomUUID(), Instant.now(), interviewId);
        }
    }

    record InterviewTermsChosen(UUID id, Instant at, UUID interviewId, List<AvailableTerm> availableTerms) implements InterviewEvent {

        public InterviewTermsChosen(UUID interviewId, List<AvailableTerm> availableTerms) {
            this(UUID.randomUUID(), Instant.now(), interviewId, availableTerms);
        }
    }
}
