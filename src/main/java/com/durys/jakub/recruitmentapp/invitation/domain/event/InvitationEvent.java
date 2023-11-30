package com.durys.jakub.recruitmentapp.invitation.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public sealed interface InvitationEvent extends DomainEvent {

    record InvitationReceived(UUID id, Instant at, UUID interviewId, UUID invitationId,
                              UUID reviewerId, List<AvailableTerm> availableTerms) implements InvitationEvent {

        public InvitationReceived(UUID interviewId, UUID invitationId, UUID reviewerId, List<AvailableTerm> availableTerms) {
            this(UUID.randomUUID(), Instant.now(), interviewId, invitationId, reviewerId, availableTerms);
        }
    }
}