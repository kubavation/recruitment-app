package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.events.DomainEvent;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface RegistrationEvent extends DomainEvent {

    record RegistrationSubmitted(UUID id, Instant at, UUID registrationId, UUID offerId, String applicantFirstName,
                                 String applicantLastName, String applicantEmail, String applicantPhoneNumber,
                                 CvId cvId) implements RegistrationEvent {

        public RegistrationSubmitted(UUID registrationId, UUID offerId, String applicantFirstName,
                                     String applicantLastName, String applicantEmail, String applicantPhoneNumber,
                                     CvId cvId) {

            this(UUID.randomUUID(), Instant.now(), registrationId, offerId, applicantFirstName, applicantLastName,
                    applicantEmail, applicantPhoneNumber, cvId);
        }
    }

    record RegistrationApproved(UUID id, Instant at, UUID registrationId, CvId cvId) implements RegistrationEvent {

        public RegistrationApproved(UUID registrationId, CvId cvId) {
            this(UUID.randomUUID(), Instant.now(), registrationId, cvId);
        }
    }

    record RegistrationRejected(UUID id, Instant at, UUID registrationId, String reason) implements RegistrationEvent {

        public RegistrationRejected(UUID registrationId, String reason) {
            this(UUID.randomUUID(), Instant.now(), registrationId, reason);
        }
    }

    record ReviewAdded(UUID id, Instant at, UUID registrationId, ReviewerId reviewerId,
                       String opinion, LocalDateTime createdAt) implements RegistrationEvent {

        public ReviewAdded(UUID registrationId, ReviewerId reviewerId, String opinion, LocalDateTime createdAt) {
            this(UUID.randomUUID(), Instant.now(), registrationId, reviewerId, opinion, createdAt);
        }
    }

}
