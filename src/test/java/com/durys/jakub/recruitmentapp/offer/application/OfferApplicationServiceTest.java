package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.offer.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OfferApplicationServiceTest {

    private OfferRepository offerRepository = Mockito.mock(OfferRepository.class);

    private final OfferApplicationService offerApplicationService = new OfferApplicationService(offerRepository);

    @Test
    void deactivateOffer_shouldChangeStatusToInactive() {

//        OfferId offerId = new OfferId(UUID.randomUUID());
////        Offer offer = new Offer(offerId,
////                new Position("CTO"),
////                new Description("CTO description"),
////                new ApplicantLimit(1),
////                new OfferPeriod(LocalDate.now(), LocalDate.now().plusYears(1)));
//
//        when(offerRepository.load(offerId)).thenReturn(offer);
//
//        offerApplicationService.deactivateOffer(offerId.value());
//
//        assertEquals(Offer.Status.INACTIVE, offer.getStatus());
    }

    @Test
    void activateOffer_shouldChangeStatusToActive() {

//        OfferId offerId = new OfferId(UUID.randomUUID());
//        Offer offer = new Offer(offerId,
//                new Position("CTO"),
//                new Description("CTO description"),
//                new ApplicantLimit(1),
//                new OfferPeriod(LocalDate.now(), LocalDate.now().plusYears(1)));
//
//        when(offerRepository.load(offerId)).thenReturn(offer);
//
//        offerApplicationService.activateOffer(offerId.value());
//
//        assertEquals(Offer.Status.ACTIVE, offer.getStatus());
    }

}