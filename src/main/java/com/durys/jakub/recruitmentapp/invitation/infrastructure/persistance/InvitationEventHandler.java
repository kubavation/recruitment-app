package com.durys.jakub.recruitmentapp.invitation.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
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
            default -> log.warn("Unsupported event {}", invitationEvent);
        }
    }


}
