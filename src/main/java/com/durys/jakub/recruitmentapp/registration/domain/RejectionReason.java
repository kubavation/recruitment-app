package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record RejectionReason(String value) {

    RejectionReason {
        test(value);
    }

    static void test(String reason) {
        if (StringUtils.isEmpty(reason)) {
            throw new ValidationException("Rejection reason cannot be empty");
        }
    }
}
