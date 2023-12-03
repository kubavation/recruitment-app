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

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
            case InterviewCompleted event -> handle(event);
            case InterviewTermsChosen event -> handle(event);
            case InvitationSent event -> handle(event);
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
        entity.setState(Interview.State.Planned.name());

        entityManager.persist(entity);
    }

    private void handle(InterviewCompleted event) {

        InterviewEntity entity = entityManager.find(InterviewEntity.class, event.interviewId());

        entity.setOpinion(event.opinion());
        entity.setAcceptation(event.acceptation());
        entity.setState(Interview.State.Completed.name());

        entityManager.persist(entity);
    }

    private void handle(InterviewTermsChosen event) {

        InterviewEntity entity = entityManager.find(InterviewEntity.class, event.interviewId());

        Set<InterviewAvailableTermEntity> terms = event.availableTerms()
                .stream()
                .map(term -> new InterviewAvailableTermEntity(UUID.randomUUID(), term.date(), term.from(), term.to()))
                .collect(Collectors.toSet());

        entity.setAvailableTerms(terms);
        entity.setState(Interview.State.Waiting.name());

        entityManager.persist(entity);
    }


    private void handle(InvitationSent event) {

        InterviewEntity entity = entityManager.find(InterviewEntity.class, event.interviewId());

        entity.setState(Interview.State.InvitationSent.name());
        entityManager.persist(entity);
    }
}
