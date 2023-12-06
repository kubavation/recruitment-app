package com.durys.jakub.recruitmentapp.interview.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InterviewEventHandlerTest {

    @Autowired
    private InterviewEventHandler interviewEventHandler;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    @Transactional
    void delete() {
        entityManager.createQuery("SELECT interview FROM InterviewEntity interview", InterviewEntity.class)
                .getResultList()
                .forEach(interview -> entityManager.remove(interview));
    }

    @Test
    void shouldCreateInterview() {

        var interviewId = UUID.randomUUID();
        var event = new InterviewEvent.InterviewInitialized(interviewId, UUID.randomUUID(), UUID.randomUUID(), "Identifier");

        interviewEventHandler.handle(event);

        assertNotNull(entityManager.find(InterviewEntity.class, interviewId));
    }

}