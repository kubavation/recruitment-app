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

    @Id
    @Column(name = "REVIEWER_ID")
    private UUID reviewerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEntity registration;

    private String opinion;

    RegistrationReviewEntity(UUID reviewerId, RegistrationEntity registration, String opinion) {
        this.reviewerId = reviewerId;
        this.registration = registration;
        this.opinion = opinion;
    }
}
