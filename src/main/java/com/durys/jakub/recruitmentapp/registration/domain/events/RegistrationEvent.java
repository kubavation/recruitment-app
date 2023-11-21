package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public sealed interface RegistrationEvent extends DomainEvent {

    record RegistrationSubmitted(UUID id, Instant at, UUID registrationId, UUID offerId, String applicantFirstName,
                                 String applicantLastName, String applicantEmail, String applicantPhoneNumber,
                                 String fileName, byte[] file) implements RegistrationEvent { //todo move file to external storage

        public RegistrationSubmitted(UUID registrationId, UUID offerId, String applicantFirstName,
                                     String applicantLastName, String applicantEmail, String applicantPhoneNumber,
                                     String fileName, byte[] file) {

            this(UUID.randomUUID(), Instant.now(), registrationId, offerId, applicantFirstName, applicantLastName,
                    applicantEmail, applicantPhoneNumber, fileName, file);
        }
    }

    record RegistrationApproved(UUID id, Instant at, UUID registrationId) implements RegistrationEvent {

        public RegistrationApproved(UUID registrationId) {
            this(UUID.randomUUID(), Instant.now(), registrationId);
        }
    }

    record RegistrationRejected(UUID id, Instant at, UUID registrationId, String reason) implements RegistrationEvent {

        public RegistrationRejected(UUID registrationId, String reason) {
            this(UUID.randomUUID(), Instant.now(), registrationId, reason);
        }
    }


}
