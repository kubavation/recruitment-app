package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OfferPeriodTest {

    @Test
    void shouldCreateOfferPeriod() {

        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 10);

        assertDoesNotThrow(() -> new OfferPeriod(from, to));
    }

    @Test
    void shouldCreateOfferPeriodWhenDateToIsNotSpecified() {

        LocalDate from = LocalDate.of(2023, 1, 1);
        assertDoesNotThrow(() -> new OfferPeriod(from, null));
    }


    @Test
    void shouldNotCreatedOfferPeriod_whenDateFromIsEmpty() {
        LocalDate to = LocalDate.of(2023, 1, 10);

        ValidationException exception = assertThrows(ValidationException.class, () -> new OfferPeriod(null, to));
        assertThat(exception.getMessage()).isEqualTo("Date from cannot be empty");
    }

    @Test
    void shouldNotCreatedOfferPeriod_whenDateFromIsLaterThanDateTo() {
        LocalDate to = LocalDate.of(2023, 1, 1);
        LocalDate from = LocalDate.of(2023, 1, 10);

        ValidationException exception = assertThrows(ValidationException.class, () -> new OfferPeriod(from, to));
        assertThat(exception.getMessage()).isEqualTo("Date from cannot be later than date from");
    }


}