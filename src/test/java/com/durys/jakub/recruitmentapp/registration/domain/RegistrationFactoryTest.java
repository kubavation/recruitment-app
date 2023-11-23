package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFactoryTest {

    @Test
    void shouldCreateRegistration() {

        Registration registration = assertDoesNotThrow(() -> RegistrationFactory.create(
                UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", new CvId(UUID.randomUUID())));

        assertNotNull(registration.id());
        assertTrue(registration.domainEvents().stream().anyMatch(event -> event instanceof RegistrationEvent.RegistrationSubmitted));
    }
}