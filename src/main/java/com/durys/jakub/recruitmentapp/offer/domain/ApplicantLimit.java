package com.durys.jakub.recruitmentapp.offer.domain;

import lombok.NonNull;

public record ApplicantLimit(Integer limit) {

    public ApplicantLimit(@NonNull Integer limit) {

        if (limit < 0) {
            throw new RuntimeException("Invalid argument");
        }

        this.limit = limit;
    }
}
