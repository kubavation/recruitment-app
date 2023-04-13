package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.*;
import com.durys.jakub.recruitmentapp.sharedkernel.identity.DomainIdentityProvider;
import com.durys.jakub.recruitmentapp.sharedkernel.identity.UUIDDomainIdentityProvider;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class OfferApplicationService {

    private final OfferRepository offerRepository;
    private final DomainIdentityProvider<UUID> identityProvider = new UUIDDomainIdentityProvider();

    public void addOffer(String position, String description, Integer applicantLimit, LocalDate from, LocalDate to) {

        Offer offer = new Offer(
                new OfferId(identityProvider.identity()),
                new Position(position),
                new Description(description),
                new ApplicantLimit(applicantLimit),
                new OfferPeriod(from, to));

        offerRepository.save(offer);
    }


}
