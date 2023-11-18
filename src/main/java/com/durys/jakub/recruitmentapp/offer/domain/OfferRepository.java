package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.ddd.DomainRepository;

public interface OfferRepository extends DomainRepository<Offer> {
    Offer load(Offer.Id offerId);
    Offer save(Offer offer);
}
