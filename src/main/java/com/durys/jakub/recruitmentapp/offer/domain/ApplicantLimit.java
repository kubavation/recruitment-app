package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;

import java.util.Objects;

record ApplicantLimit(Integer limit) {

    ApplicantLimit {
        test(limit);
    }

    static void test(Integer limit) {

        if (Objects.isNull(limit)) {
            throw new ValidationException("Applicant limit cannot be empty");
        }

        if (limit <= 0) {
            throw new ValidationException("Applicant limit cannot be less than 1");
        }

    }
}
