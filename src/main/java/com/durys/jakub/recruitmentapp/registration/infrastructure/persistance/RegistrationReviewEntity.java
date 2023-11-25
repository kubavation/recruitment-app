package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "OFFER_REGISTRATION_REVIEW")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
public class RegistrationReviewEntity {

    @EmbeddedId
    private final RegistrationReviewEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("registrationId")
    private RegistrationEntity registration;

    private String opinion;


    RegistrationReviewEntity(RegistrationReviewEntityId id, RegistrationEntity registration, String opinion) {
        this.id = id;
        this.registration = registration;
        this.opinion = opinion;
    }

    RegistrationReviewEntity(UUID reviewerId, RegistrationEntity registration, String opinion) {
        this.id = new RegistrationReviewEntityId(registration.getId(), reviewerId);
        this.registration = registration;
        this.opinion = opinion;
    }
}
