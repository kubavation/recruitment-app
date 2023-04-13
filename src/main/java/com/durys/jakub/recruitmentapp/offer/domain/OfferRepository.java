package com.durys.jakub.recruitmentapp.offer.domain;

public interface OfferRepository {
    Offer load(OfferId offerId);
    void save(Offer offer);
}
