package com.durys.jakub.recruitmentapp.offer.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfferFactory {

    public static Offer create(String position, String description, Integer applicantLimit, LocalDate from, LocalDate to) {
        return new Offer(
                new Position(position),
                new Description(description),
                new ApplicantLimit(applicantLimit),
                new OfferPeriod(from, to)
        );
    }

    public static Offer create(UUID id, String position, String description, Integer applicantLimit,
                               LocalDate from, LocalDate to, String state) {
        return new Offer(
                new Offer.Id(id),
                new Position(position),
                new Description(description),
                new ApplicantLimit(applicantLimit),
                new OfferPeriod(from, to),
                Offer.Status.valueOf(state)
        );
    }

}
