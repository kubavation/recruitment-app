package com.durys.jakub.recruitmentapp.offer.domain;

public interface OfferRepository {
    Offer load(Offer.Id offerId);
    void save(Offer offer);
}
