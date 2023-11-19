package com.durys.jakub.recruitmentapp.events;

import com.durys.jakub.recruitmentapp.offer.application.OfferApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DomainEventsAspectTest {

    @MockBean
    private EventEmitter eventEmitter;

    @Autowired
    private OfferApplicationService offerApplicationService;


    @Test
    void shouldEmitEvents() {
        var command = new AddOfferCommand("Position", "Description", 1,
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3));

        offerApplicationService.handle(command);

        verify(eventEmitter).emit(any());
    }

}