package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.offer.domain.*;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;
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

}