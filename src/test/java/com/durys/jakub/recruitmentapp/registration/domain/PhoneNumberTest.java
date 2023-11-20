package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreatePhoneNumber() {
        assertDoesNotThrow(() -> new PhoneNumber("533212343"));
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsEmpty() {
        var exception = assertThrows(ValidationException.class, () -> new PhoneNumber(""));
        assertEquals("Phone number cannot be empty", exception.getMessage());
    }
}