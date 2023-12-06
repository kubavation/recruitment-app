package com.durys.jakub.recruitmentapp.invitation.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InvitationEventHandlerTest {

    @Autowired
    private InvitationEventHandler invitationEventHandler;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    @Transactional
    void delete() {
        entityManager.createQuery("SELECT invitation FROM InvitationEntity invitation", InvitationEntity.class)
                .getResultList()
                .forEach(interview -> entityManager.remove(interview));
    }

    @Test
    @Transactional
    void shouldCreateInvitation() {

        var invitationId = UUID.randomUUID();

        var event = new InvitationEvent.InvitationReceived(UUID.randomUUID(), invitationId, UUID.randomUUID(), List.of());

        invitationEventHandler.handle(event);

        assertNotNull(entityManager.find(InvitationEntity.class, invitationId));
    }

    @Test
    @Transactional
    void shouldAcceptInvitation() {

        var invitationId = addInvitation();
        var term = LocalDate.of(2023, 10, 10).atTime(10, 0);

        var event = new InvitationEvent.InvitationAccepted(UUID.randomUUID(), invitationId, term, UUID.randomUUID());

        invitationEventHandler.handle(event);

        InvitationEntity entity = entityManager.find(InvitationEntity.class, invitationId);
        assertEquals("Closed", entity.getState());
        assertEquals(term, entity.getAt());
    }

    @Test
    @Transactional
    void shouldRejectInvitation() {

        var invitationId = addInvitation();

        String rejectionReason = "Absence";

        var event = new InvitationEvent.InvitationRejected(UUID.randomUUID(), invitationId, UUID.randomUUID(), rejectionReason);

        invitationEventHandler.handle(event);

        InvitationEntity entity = entityManager.find(InvitationEntity.class, invitationId);
        assertEquals("Closed", entity.getState());
        assertEquals(rejectionReason, entity.getReason());
    }

    private UUID addInvitation() {

        var invitation = new InvitationEntity(UUID.randomUUID(), null, null, null, "New", null);

        entityManager.persist(invitation);
        return invitation.getId();
    }

}