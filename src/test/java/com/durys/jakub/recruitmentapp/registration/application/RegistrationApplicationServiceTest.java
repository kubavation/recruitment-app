package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferFactory;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationFactory;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import com.durys.jakub.recruitmentapp.registration.domain.command.ApproveRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.RejectRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.SubmitRegistrationCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationApplicationServiceTest {

    private final RegistrationRepository registrationRepository = mock(RegistrationRepository.class);
    private final OfferRepository offerRepository = mock(OfferRepository.class);

    private final RegistrationApplicationService service
            = new RegistrationApplicationService(registrationRepository, offerRepository);


    @Test
    void shouldSubmitRegistration() {

        var command = new SubmitRegistrationCommand(UUID.randomUUID(), "John", "Doe",
                "johndoe@gmail.com", "333222333", "cv.pdf", new byte[] {});

        when(offerRepository.load(new Offer.Id(command.offerId()))).thenReturn(
               OfferFactory.create("IT specialist",
                        "Description", 2, LocalDate.now(), null)
        );

        service.handle(command);

        verify(registrationRepository).save(Mockito.any(Registration.class));
    }

    @Test
    void shouldNotSubmitRegistration_whenOfferIsClosed() {

        var command = new SubmitRegistrationCommand(UUID.randomUUID(), "John", "Doe",
                "johndoe@gmail.com", "333222333", "cv.pdf", new byte[] {});

        when(offerRepository.load(new Offer.Id(command.offerId()))).thenReturn(
                OfferFactory.create(UUID.randomUUID(), "Position",
                        "Description", 2, LocalDate.now(), null, Offer.Status.Closed.name())
        );


        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.handle(command));

        assertEquals("Cannot register application to this offer", exception.getMessage());
        verifyNoInteractions(registrationRepository);
    }

    @Test
    void shouldRejectRegistration() {

        var registrationId = new Registration.Id(UUID.randomUUID());
        var command = new RejectRegistrationCommand(registrationId.value(), "Reason");

        when(registrationRepository.load(registrationId)).thenReturn(
                RegistrationFactory.create(
                    UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                    "430212343", "cv.pdf", new byte[]{}));

        service.handle(command);

        verify(registrationRepository).save(Mockito.any(Registration.class));
    }


    @Test
    void shouldApproveRegistration() {

        var registrationId = new Registration.Id(UUID.randomUUID());
        var command = new ApproveRegistrationCommand(registrationId.value());

        when(registrationRepository.load(registrationId)).thenReturn(
                RegistrationFactory.create(
                        UUID.randomUUID(), "John", "Doe", "jondoe@gmail.com",
                        "430212343", "cv.pdf", new byte[]{}));

        service.handle(command);

        verify(registrationRepository).save(Mockito.any(Registration.class));
    }

}