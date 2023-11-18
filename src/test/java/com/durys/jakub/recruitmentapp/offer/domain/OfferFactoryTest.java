package com.durys.jakub.recruitmentapp.offer.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OfferFactoryTest {

    @Test
    void shouldCreateOffer() {

        Offer offer = OfferFactory.create("IT specialist",
                "Description", 2, LocalDate.now(), null);

        assertNotNull(offer.id());
    }
}