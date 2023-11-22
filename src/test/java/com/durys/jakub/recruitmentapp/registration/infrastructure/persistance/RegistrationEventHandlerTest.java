package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RegistrationEventHandlerTest {

    @Autowired
    private RegistrationEventHandler registrationEventHandler;

    @Autowired
    EntityManager entityManager;

    private static UUID offerId = UUID.randomUUID();

    @BeforeEach
    @Transactional
    void delete() {
        entityManager.createQuery("SELECT registration FROM RegistrationEntity registration", RegistrationEntity.class)
                .getResultList()
                .forEach(offer -> entityManager.remove(offer));
    }

    void init() {
        OfferEntity offer = new OfferEntity(offerId, "IT specialist", "Description",
                1, LocalDate.of(2023, 1, 1), null, Offer.Status.Published.name());
        entityManager.persist(offer);
    }

    @Test
    @Transactional
    void shouldCreateRegistrationEntity() {

        var id = UUID.randomUUID();
        init();

        var event = new RegistrationEvent.RegistrationSubmitted(
            id, offerId, "John", "Doe", "johndoe@gmail.com", "321321211",
            "cv.pdf", new byte[] {} );

        registrationEventHandler.handle(event);

        assertNotNull(entityManager.find(RegistrationEntity.class, id));
    }

    @Test
    @Transactional
    void shouldApproveRegistrationEntity() {

        UUID registrationId = addRegistration();

        var event = new RegistrationEvent.RegistrationApproved(registrationId);

        registrationEventHandler.handle(event);

        assertThat(entityManager.find(RegistrationEntity.class, registrationId).getStatus()).isEqualTo(Registration.Status.Approved.name());
    }

    private UUID addRegistration() {

        OfferEntity offer = entityManager.find(OfferEntity.class, offerId);

        RegistrationEntity registration = new RegistrationEntity(
                UUID.randomUUID(), offer,
                new ApplicantInformation("John", "Doe","johndoe@gmail.com", "321321211"),
                "cv.pdf", null, new byte[] {},
                Registration.Status.Submitted.name());

        entityManager.persist(registration);
        entityManager.flush();
        return registration.getId();
    }

}