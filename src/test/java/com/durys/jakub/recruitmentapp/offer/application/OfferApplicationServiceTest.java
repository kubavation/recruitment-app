package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.offer.domain.*;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;
import com.durys.jakub.recruitmentapp.offer.domain.command.CloseOfferCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OfferApplicationServiceTest {

    private final OfferRepository offerRepository = Mockito.mock(OfferRepository.class);

    private final OfferApplicationService offerApplicationService = new OfferApplicationService(offerRepository);

    @Test
    void shouldCreateOffer() {
        var command = new AddOfferCommand("Position", "Description", 1,
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3));

        offerApplicationService.handle(command);

        verify(offerRepository, times(1)).save(Mockito.any(Offer.class));
    }

    @Test
    void shouldCloseOffer() {
        var offerId = new Offer.Id(UUID.randomUUID());
        var command = new CloseOfferCommand(offerId, LocalDate.of(2023, 10, 10).atStartOfDay());

        when(offerRepository.load(offerId))
                .thenReturn(
                        OfferFactory.create("IT specialist", "Description", 2, LocalDate.now(), null));

        offerApplicationService.handle(command);

        verify(offerRepository, times(1)).save(Mockito.any(Offer.class));
    }

}