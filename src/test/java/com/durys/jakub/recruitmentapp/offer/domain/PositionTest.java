package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void shouldCreatePosition() {
        assertDoesNotThrow(() -> new Position("IT specialist"));
    }

    @Test
    void shouldNotCreatePosition_whenValueIsEmpty() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Position(null));
        assertThat(exception.getMessage()).isEqualTo("Position cannot be empty");
    }


}