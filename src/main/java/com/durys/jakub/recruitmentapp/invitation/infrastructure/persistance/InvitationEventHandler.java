package com.durys.jakub.recruitmentapp.invitation.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.infrastructure.persistance.InterviewEntity;
import com.durys.jakub.recruitmentapp.invitation.domain.Invitation;
import com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class InvitationEventHandler implements EventHandler<InvitationEvent> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void handle(InvitationEvent invitationEvent) {
        switch (invitationEvent) {
            case InvitationEvent.InvitationReceived event -> handle(event);
            case InvitationEvent.InvitationAccepted event -> handle(event);
            case InvitationEvent.InvitationRejected event -> handle(event);
            default -> log.warn("Unsupported event {}", invitationEvent);
        }
    }

    private void handle(InvitationEvent.InvitationReceived event) {

        InterviewEntity interview = entityManager.find(InterviewEntity.class, event.interviewId());

        InvitationEntity invitation = new InvitationEntity(event.invitationId(), interview, event.reviewerId());

        entityManager.persist(invitation);
    }

    private void handle(InvitationEvent.InvitationAccepted event) {

        InvitationEntity invitation = entityManager.find(InvitationEntity.class, event.invitationId());

        invitation.setAt(event.term());
        invitation.setState(Invitation.State.Closed.name());

        entityManager.persist(invitation);
    }

    private void handle(InvitationEvent.InvitationRejected event) {

        InvitationEntity invitation = entityManager.find(InvitationEntity.class, event.invitationId());

        invitation.setReason(event.reason());
        invitation.setState(Invitation.State.Closed.name());

        entityManager.persist(invitation);
    }


}
