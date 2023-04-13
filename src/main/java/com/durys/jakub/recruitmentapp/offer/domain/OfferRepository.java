package com.durys.jakub.recruitmentapp.offer.domain;

public interface OfferRepository {
    OfferId load(OfferId offerId);
    void save(Offer offer);
}
