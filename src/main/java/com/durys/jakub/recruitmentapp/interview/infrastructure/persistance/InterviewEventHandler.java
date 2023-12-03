package com.durys.jakub.recruitmentapp.interview.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.registration.infrastructure.persistance.RegistrationEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent.*;

@Component
@Slf4j
@RequiredArgsConstructor
class InterviewEventHandler implements EventHandler<InterviewEvent> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void handle(InterviewEvent interviewEvent) {
        switch (interviewEvent) {
            case InterviewInitialized event -> handle(event);
            case ReviewerAssigned event -> handle(event);
            default -> log.warn("Unsupported event {}", interviewEvent);
        }
    }

    private void handle(InterviewInitialized event) {

        RegistrationEntity registration = entityManager.find(RegistrationEntity.class, event.registrationId());

        InterviewEntity entity = new InterviewEntity(event.interviewId(), event.identifier(),
                registration, event.tenantId(), Interview.State.New.name());

        entityManager.persist(entity);
    }

    private void handle(ReviewerAssigned event) {

        InterviewEntity entity = entityManager.find(InterviewEntity.class, event.interviewId());

        entity.setReviewerId(event.reviewerId());
        entity.setAt(event.interviewAt());

        entityManager.persist(entity);
    }
}
