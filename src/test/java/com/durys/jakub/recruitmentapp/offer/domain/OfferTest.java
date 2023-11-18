package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import static com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void shouldPublishOffer() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.New);

        offer.publish();
        assertTrue(offer.domainEvents().stream().anyMatch(event -> event instanceof OfferPublished));
        assertThat(offer.state()).isEqualTo(Offer.Status.Published);
    }

    @Test
    void shouldNotPublishOffer_whenIsAlreadyPublished() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.Published);

        InvalidStateForOperationException exception = assertThrows(InvalidStateForOperationException.class, offer::publish);
        assertThat(exception.getMessage()).isEqualTo("Offer cannot be published");
    }


    @Test
    void shouldCloseOffer() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.Published);
        LocalDateTime closedAt = LocalDateTime.now();

        offer.close(closedAt);
        assertTrue(offer.domainEvents().stream().anyMatch(event -> event instanceof OfferClosed));
        assertThat(offer.state()).isEqualTo(Offer.Status.Closed);
    }

    @Test
    void shouldNotPublishOffer_whenIsAlreadyClosed() {

        Offer offer = new Offer(new Offer.Id(UUID.randomUUID()), new Position("IT specialist"), new Description("Description"),
                new ApplicantLimit(2), new OfferPeriod(LocalDate.now(), null), Offer.Status.Closed);
        LocalDateTime closedAt = LocalDateTime.now();

        InvalidStateForOperationException exception = assertThrows(InvalidStateForOperationException.class, () -> offer.close(closedAt));
        assertThat(exception.getMessage()).isEqualTo("Offer cannot be closed");
    }

}