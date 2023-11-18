package com.durys.jakub.recruitmentapp.offer.domain;

import java.time.LocalDate;

public class OfferFactory {

    public static Offer create(String position, String description, Integer applicantLimit, LocalDate from, LocalDate to) {
        return new Offer(
                new Position(position),
                new Description(description),
                new ApplicantLimit(applicantLimit),
                new OfferPeriod(from, to)
        );
    }

}
