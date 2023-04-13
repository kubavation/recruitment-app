package com.durys.jakub.recruitmentapp.offer.domain;

import lombok.NonNull;

import java.time.LocalDate;

public record OfferPeriod(@NonNull LocalDate from, @NonNull LocalDate to) {
}
