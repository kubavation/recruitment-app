package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record PhoneNumber(String value) {

    PhoneNumber {
        test(value);
    }

    static void test(String phoneNumber) {

        if (StringUtils.isEmpty(phoneNumber)) {
            throw new ValidationException("Phone number cannot be empty");
        }

        //todo additional validation
    }
}
