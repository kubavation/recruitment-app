package com.durys.jakub.recruitmentapp.waitingroom.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;


    public void register() {

    }

    public void decline(UUID registrationId) {

    }

    public void accept(UUID registrationId) {

    }


}
