package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import static com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent.*;

import com.durys.jakub.recruitmentapp.cv.CvId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RegistrationTest {

    @Test
    void shouldRejectRegistration() {

        String rejectReason = "Reason";

        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Submitted");

        registration.reject(rejectReason);

        assertEquals(Registration.Status.Rejected, registration.state());
        assertEquals(rejectReason, registration.rejectReason());
        assertTrue(registration.domainEvents().stream().anyMatch(event -> event instanceof RegistrationRejected));
    }

    @Test
    void shouldNotRejectedRegistration_whenStateIsAlreadyRejected() {

        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Rejected");

        RuntimeException exception = assertThrows(InvalidStateForOperationException.class, () -> registration.reject("Reason"));

        assertEquals("Registration cannot be rejected", exception.getMessage());
    }

    @Test
    void shouldNotRejectedRegistration_whenStateIsAlreadyAccepted() {

        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Approved");

        RuntimeException exception = assertThrows(InvalidStateForOperationException.class, () -> registration.reject("Reason"));

        assertEquals("Registration cannot be rejected", exception.getMessage());
    }



    @Test
    void shouldApproveRegistration() {


        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Submitted");

        registration.approve();

        assertEquals(Registration.Status.Approved, registration.state());
        assertTrue(registration.domainEvents().stream().anyMatch(event -> event instanceof RegistrationApproved));
    }

    @Test
    void shouldNotApprovedRegistration_whenStateIsAlreadyApproved() {

        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Approved");

        RuntimeException exception = assertThrows(InvalidStateForOperationException.class, registration::approve);

        assertEquals("Registration cannot be approved", exception.getMessage());
    }


    @Test
    void shouldNotApprovedRegistration_whenStateIsAlreadyRejected() {

        Registration registration = RegistrationFactory.create(
                UUID.randomUUID(), UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID()), null, "Rejected");

        RuntimeException exception = assertThrows(InvalidStateForOperationException.class, registration::approve);

        assertEquals("Registration cannot be approved", exception.getMessage());
    }

}