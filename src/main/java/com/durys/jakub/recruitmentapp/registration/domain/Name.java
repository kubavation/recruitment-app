package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record Name(String firstName, String lastName) {

    Name {
        test(firstName, lastName);
    }


    static void test(String firstName, String lastName) {

        if (StringUtils.isEmpty(firstName)) {
            throw new ValidationException("First name cannot be empty");
        }

        if (StringUtils.isEmpty(lastName)) {
            throw new ValidationException("Last name cannot be empty");
        }

    }
}
