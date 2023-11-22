package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

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
            default -> log.warn("Unsupported event {}", registrationEvent);
        }
    }

    void handle(RegistrationSubmitted event) {

        OfferEntity offer = entityManager.find(OfferEntity.class, event.offerId());

        ApplicantInformation applicantInformation = new ApplicantInformation(
                event.applicantFirstName(), event.applicantLastName(), event.applicantEmail(), event.applicantPhoneNumber());

        RegistrationEntity registration = new RegistrationEntity(event.id(), offer,
                applicantInformation, null, event.fileName(), event.file(), Registration.Status.Submitted.name());

        entityManager.persist(registration);
    }

    void handle(RegistrationApproved event) {

        RegistrationEntity registration = entityManager.find(RegistrationEntity.class, event.registrationId());
        registration.setStatus(Registration.Status.Approved.name());

        entityManager.persist(registration);
    }

    void handle(RegistrationRejected event) {

    }

}
