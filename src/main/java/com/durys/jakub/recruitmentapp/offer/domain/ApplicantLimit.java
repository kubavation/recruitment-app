package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;

import java.util.Objects;

record ApplicantLimit(Integer value) {

    ApplicantLimit {
        test(value);
    }

    static void test(Integer limit) {

        if (Objects.isNull(limit)) {
            throw new ValidationException("Applicant value cannot be empty");
        }

        if (limit <= 0) {
            throw new ValidationException("Applicant value cannot be less than 1");
        }

    }
}
