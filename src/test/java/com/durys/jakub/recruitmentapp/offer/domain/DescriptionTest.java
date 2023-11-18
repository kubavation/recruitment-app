package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void shouldCreateDescription() {
        assertDoesNotThrow(() -> new Description("Description"));
    }

    @Test
    void shouldNotCreateDescription_whenValueIsEmpty() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Description(null));
        assertThat(exception.getMessage()).isEqualTo("Description cannot be empty");
    }

}