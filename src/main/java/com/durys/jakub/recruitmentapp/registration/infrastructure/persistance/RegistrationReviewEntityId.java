package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
class RegistrationReviewEntityId implements Serializable {

    @Column(name = "REGISTRATION_ID")
    private final UUID registrationId;

    @Column(name = "REVIEWER_IR")
    private final UUID reviewerId;


    RegistrationReviewEntityId(UUID registrationId, UUID reviewerId) {
        this.registrationId = registrationId;
        this.reviewerId = reviewerId;
    }
}
