package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.offer.domain.event.OfferPublished;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void shouldPublishOffer() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.New);

        OfferPublished event = offer.publish();
        assertThat(new Offer.Id(event.offerId())).isEqualTo(offer.id());
    }

    @Test
    void shouldNotPublishOffer_whenIsAlreadyPublished() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.Published);

        RuntimeException exception = assertThrows(RuntimeException.class, offer::publish);
        assertThat(exception.getMessage()).isEqualTo("Offer cannot be published");
    }

}