package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record Description(String value) {

    Description {
        test(value);
    }

    private static void test(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException("Description cannot be empty");
        }
    }

}
