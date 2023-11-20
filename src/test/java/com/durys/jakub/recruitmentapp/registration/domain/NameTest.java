package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void shouldCreateName() {
        assertDoesNotThrow(() -> new Name("John", "Doe"));
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsEmpty() {
        var exception = assertThrows(ValidationException.class, () -> new Name("", "Doe"));
        assertEquals("First name cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsEmpty() {
        var exception = assertThrows(ValidationException.class, () -> new Name("John", ""));
        assertEquals("Last name cannot be empty", exception.getMessage());
    }

}