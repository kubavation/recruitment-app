package com.durys.jakub.recruitmentapp.registration.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFactoryTest {

    @Test
    void shouldCreateRegistration() {

        Registration registration = assertDoesNotThrow(() -> RegistrationFactory.create(
                UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                "430212343", "cv.pdf", new byte[]{}));

        assertNotNull(registration.id());
    }
}