package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private final RegistrationEntity registration;

    private final String opinion;

    @Column(name = "CREATED_AT")
    private final LocalDateTime createdAt;


    RegistrationReviewEntity(RegistrationReviewEntityId id, RegistrationEntity registration,
                             String opinion, LocalDateTime createdAt) {
        this.id = id;
        this.registration = registration;
        this.opinion = opinion;
        this.createdAt = createdAt;
    }

    RegistrationReviewEntity(UUID reviewerId, RegistrationEntity registration, String opinion, LocalDateTime createdAt) {
        this.id = new RegistrationReviewEntityId(registration.getId(), reviewerId);
        this.registration = registration;
        this.opinion = opinion;
        this.createdAt = createdAt;
    }
}
