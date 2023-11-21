package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RejectionReasonTest {

    @Test
    void shouldCreateRejectionReason() {
        assertDoesNotThrow(() -> new RejectionReason("Reason"));
    }

    @Test
    void shouldThrowExceptionWhenRejectionReasonIsEmpty() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new RejectionReason(""));
        assertEquals("Rejection reason cannot be empty", exception.getMessage());
    }
}