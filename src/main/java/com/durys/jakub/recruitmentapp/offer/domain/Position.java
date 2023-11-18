package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import org.apache.commons.lang.StringUtils;

record Position(String name) {

    Position {
        test(name);
    }

    private static void test(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException("Position cannot be empty");
        }
    }
}
