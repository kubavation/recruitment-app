package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationFactory;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import com.durys.jakub.recruitmentapp.registration.domain.command.ApproveRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.RejectRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.SubmitRegistrationCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.*;

class RegistrationApplicationServiceTest {

    private final RegistrationRepository registrationRepository = mock(RegistrationRepository.class);

    private final RegistrationApplicationService service = new RegistrationApplicationService(registrationRepository);


    @Test
    void shouldSubmitRegistration() {

        var command = new SubmitRegistrationCommand(UUID.randomUUID(), "John", "Doe",
                "johndoe@gmail.com", "333222333", "cv.pdf", new byte[] {});

        service.handle(command);

        verify(registrationRepository).save(Mockito.any(Registration.class));
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