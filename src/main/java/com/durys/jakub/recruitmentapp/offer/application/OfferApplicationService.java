package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferFactory;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;

@ApplicationService
public class OfferApplicationService {

    private final OfferRepository offerRepository;

    public OfferApplicationService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    void handle(AddOfferCommand command) {

        Offer offer = OfferFactory.create(command.position(), command.description(),
                command.applicantLimit(), command.from(), command.to());

        offerRepository.save(offer);
    }

}
