package com.durys.jakub.recruitmentapp.interview.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @Transactional
    void shouldAssignReviewer() {

        var interviewId = addInterview("Waiting");
        var reviewerId = UUID.randomUUID();
        var term = LocalDate.of(2023, 12, 12).atTime(8, 30);


        var event = new InterviewEvent.ReviewerAssigned(interviewId, reviewerId, term);

        interviewEventHandler.handle(event);

        InterviewEntity entity = entityManager.find(InterviewEntity.class, interviewId);
        assertEquals(reviewerId, entity.getReviewerId());
        assertEquals(term, entity.getAt());
    }

    @Test
    @Transactional
    void shouldCompleteInterview() {

        var interviewId = addInterview("Waiting");
        var opinion = "Opinion1";
        boolean acceptation = false;


        var event = new InterviewEvent.InterviewCompleted(interviewId, opinion, acceptation);

        interviewEventHandler.handle(event);

        InterviewEntity entity = entityManager.find(InterviewEntity.class, interviewId);
        assertEquals(acceptation, entity.isAcceptation());
        assertEquals(opinion, entity.getOpinion());
    }

    @Test
    @Transactional
    void shouldChooseAvailableTerms() {

        var interviewId = addInterview("New");
        var availableTerms = List.of(
            new AvailableTerm(LocalDate.now(), LocalTime.of(8, 0), LocalTime.of(9, 0))
        );


        var event = new InterviewEvent.InterviewTermsChosen(interviewId, availableTerms);

        interviewEventHandler.handle(event);

        InterviewEntity entity = entityManager.find(InterviewEntity.class, interviewId);
        assertFalse(entity.getAvailableTerms().isEmpty());
    }


    private UUID addInterview(String state) {

        InterviewEntity entity = new InterviewEntity(UUID.randomUUID(), "Identifier", null, UUID.randomUUID(), LocalDateTime.now(),
                UUID.randomUUID(), state, "opinion", false, new HashSet<>());

        entityManager.persist(entity);
        return entity.getId();
    }

}