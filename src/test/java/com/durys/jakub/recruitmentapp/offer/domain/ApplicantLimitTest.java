package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApplicantLimitTest {

    @Test
    void shouldCreateApplicantLimit() {
        assertDoesNotThrow(() -> new ApplicantLimit(2));
    }

    @Test
    void shouldNotCreateApplicantLimit_whenLimitIsEmpty() {

        ValidationException exception = assertThrows(ValidationException.class, () -> new ApplicantLimit(null));
        assertThat(exception.getMessage()).isEqualTo("Applicant value cannot be empty");
    }


    @Test
    void shouldNotCreateApplicantLimit_whenLimitIsLessThanOne() {

        ValidationException exception = assertThrows(ValidationException.class, () -> new ApplicantLimit(0));
        assertThat(exception.getMessage()).isEqualTo("Applicant value cannot be less than 1");
    }


}