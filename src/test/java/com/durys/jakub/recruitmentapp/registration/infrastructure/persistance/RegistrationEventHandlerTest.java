package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.cv.Cv;
import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private static UUID cvId = UUID.randomUUID();

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

        Cv cv = new Cv(new CvId(cvId), "cv.pdf", new byte[] {});
        entityManager.persist(cv);
    }

    @Test
    @Transactional
    void shouldCreateRegistrationEntity() {

        var id = UUID.randomUUID();
        init();

        var event = new RegistrationEvent.RegistrationSubmitted(
            id, offerId, "John", "Doe", "johndoe@gmail.com", "321321211",
                new CvId(UUID.randomUUID()) );

        registrationEventHandler.handle(event);

        assertNotNull(entityManager.find(RegistrationEntity.class, id));
    }

    @Test
    @Transactional
    void shouldApproveRegistrationEntity() {

        UUID registrationId = addRegistration();

        var event = new RegistrationEvent.RegistrationApproved(registrationId, new CvId(cvId));

        registrationEventHandler.handle(event);

        assertThat(entityManager.find(RegistrationEntity.class, registrationId).getStatus()).isEqualTo(Registration.Status.Approved.name());
    }

    @Test
    @Transactional
    void shouldRejectRegistrationEntity() {

        UUID registrationId = addRegistration();

        var event = new RegistrationEvent.RegistrationRejected(registrationId, "Reason");

        registrationEventHandler.handle(event);

        assertThat(entityManager.find(RegistrationEntity.class, registrationId).getStatus()).isEqualTo(Registration.Status.Rejected.name());
    }

    @Test
    @Transactional
    void shouldAddRegistrationReviewEntity() {

        UUID registrationId = addRegistration();
        UUID reviewerId = UUID.randomUUID();
        String opinion = "Opinion";

        var event = new RegistrationEvent.ReviewAdded(registrationId, new ReviewerId(reviewerId), opinion, LocalDateTime.now());

        registrationEventHandler.handle(event);

        RegistrationReviewEntity review = entityManager
                .find(RegistrationReviewEntity.class, new RegistrationReviewEntityId(registrationId, reviewerId));

        assertNotNull(review);
        assertThat(review.getOpinion()).isEqualTo(opinion);
    }


    @Test
    @Transactional
    void shouldUpdateRegistrationReviewEntity() {

        UUID registrationId = addRegistration();
        UUID reviewerId = UUID.randomUUID();
        String opinion = "Opinion";
        String secondOpinion = "Opinion2";

        var event = new RegistrationEvent.ReviewAdded(registrationId, new ReviewerId(reviewerId), opinion, LocalDateTime.now());
        registrationEventHandler.handle(event);

        var secondEvent = new RegistrationEvent.ReviewAdded(registrationId, new ReviewerId(reviewerId), secondOpinion, LocalDateTime.now());
        registrationEventHandler.handle(secondEvent);

        RegistrationReviewEntity review = entityManager
                .find(RegistrationReviewEntity.class, new RegistrationReviewEntityId(registrationId, reviewerId));

        assertNotNull(review);
        assertThat(review.getOpinion()).isEqualTo(secondOpinion);
    }

    private UUID addRegistration() {

        OfferEntity offer = entityManager.find(OfferEntity.class, offerId);
        Cv cv = entityManager.find(Cv.class, new CvId(cvId));

        RegistrationEntity registration = new RegistrationEntity(
                UUID.randomUUID(), offer,
                new ApplicantInformation("John", "Doe","johndoe@gmail.com", "321321211"),
                null, cv,
                Registration.Status.Submitted.name());

        entityManager.persist(registration);
        entityManager.flush();
        return registration.getId();
    }

}