package com.durys.jakub.recruitmentapp.offer.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Offer {

    private final OfferId offerId;
    private final Position position;
    private final Description description;
    private final ApplicantLimit limit;
    private final OfferPeriod period;

}
