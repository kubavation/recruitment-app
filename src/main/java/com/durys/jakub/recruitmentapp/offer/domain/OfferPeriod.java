package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;

import java.time.LocalDate;
import java.util.Objects;

record OfferPeriod(LocalDate from, LocalDate to) {

    OfferPeriod {
        test(from, to);
    }

    static void test(LocalDate from, LocalDate to) {

        if (Objects.isNull(from)) {
            throw new ValidationException("Date from cannot be empty");
        }

        if (Objects.nonNull(to) && from.isAfter(to)) {
            throw new ValidationException("Date from cannot be later than date from");
        }

    }
}
