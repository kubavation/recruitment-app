package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateEmail() {
        assertDoesNotThrow(() -> new Email("valid@gmail.com"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        var exception = assertThrows(ValidationException.class, () -> new Email("valid@gmail.com"));
        assertEquals("Email cannot be empty", exception.getMessage());
    }

}