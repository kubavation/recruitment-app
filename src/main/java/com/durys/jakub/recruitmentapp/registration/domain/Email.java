package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record Email(String value) {

    Email {
        test(value);
    }

    static void test(String email) {

        if (StringUtils.isEmpty(email)) {
            throw new ValidationException("Email cannot be empty");
        }

        //todo additional validation
    }
}
