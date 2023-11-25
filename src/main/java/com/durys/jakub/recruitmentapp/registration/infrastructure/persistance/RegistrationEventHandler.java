package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.cv.Cv;
import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent.*;

@Component
@Slf4j
class RegistrationEventHandler implements EventHandler<RegistrationEvent> {

    private final EntityManager entityManager;

    RegistrationEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void handle(RegistrationEvent registrationEvent) {
        switch (registrationEvent) {
            case RegistrationApproved approved -> handle(approved);
            case RegistrationRejected rejected -> handle(rejected);
            case RegistrationSubmitted submitted -> handle(submitted);
            case ReviewAdded reviewAdded -> handle(reviewAdded);
            default -> log.warn("Unsupported event {}", registrationEvent);
        }
    }

    void handle(RegistrationSubmitted event) {

        OfferEntity offer = entityManager.find(OfferEntity.class, event.offerId());

        Cv cv = entityManager.find(Cv.class, event.cvId());

        ApplicantInformation applicantInformation = new ApplicantInformation(
                event.applicantFirstName(), event.applicantLastName(), event.applicantEmail(), event.applicantPhoneNumber());

        RegistrationEntity registration = new RegistrationEntity(event.registrationId(), offer,
                applicantInformation, null, cv, Registration.Status.Submitted.name());

        entityManager.persist(registration);
    }

    void handle(RegistrationApproved event) {

        RegistrationEntity registration = entityManager.find(RegistrationEntity.class, event.registrationId());

        registration.setStatus(Registration.Status.Approved.name());

        entityManager.persist(registration);
    }

    void handle(RegistrationRejected event) {

        RegistrationEntity registration = entityManager.find(RegistrationEntity.class, event.registrationId());

        registration.setStatus(Registration.Status.Rejected.name());
        registration.setRejectionReason(event.reason());

        entityManager.persist(registration);
    }

    void handle(ReviewAdded event) {

        RegistrationEntity registration = entityManager.find(RegistrationEntity.class, event.registrationId());

        RegistrationReviewEntity review = new RegistrationReviewEntity(event.reviewerId().value(), registration,
                event.opinion(), event.createdAt());

        entityManager.persist(review);
    }

}
