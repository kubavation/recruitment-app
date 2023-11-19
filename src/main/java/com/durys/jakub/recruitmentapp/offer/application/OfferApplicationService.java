package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.ddd.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferFactory;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;
import com.durys.jakub.recruitmentapp.offer.domain.command.CloseOfferCommand;
import com.durys.jakub.recruitmentapp.offer.domain.command.PublishOfferCommand;
import jakarta.transaction.Transactional;

@ApplicationService
public class OfferApplicationService {

    private final OfferRepository offerRepository;

    public OfferApplicationService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Transactional
    public void handle(AddOfferCommand command) {

        Offer offer = OfferFactory.create(command.position(), command.description(),
                command.applicantLimit(), command.from(), command.to());

        offerRepository.save(offer);
    }

    @Transactional
    public void handle(CloseOfferCommand command) {

        Offer offer = offerRepository.load(command.offerId());

        offer.close(command.at());
        offerRepository.save(offer);
    }

    @Transactional
    public void handle(PublishOfferCommand command) {

        Offer offer = offerRepository.load(command.offerId());

        offer.publish();
        offerRepository.save(offer);
    }
}
