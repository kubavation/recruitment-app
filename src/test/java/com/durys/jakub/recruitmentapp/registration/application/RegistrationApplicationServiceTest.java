package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import com.durys.jakub.recruitmentapp.registration.domain.command.SubmitRegistrationCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

}