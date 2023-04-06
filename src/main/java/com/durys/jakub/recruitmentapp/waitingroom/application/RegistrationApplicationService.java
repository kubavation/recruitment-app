package com.durys.jakub.recruitmentapp.waitingroom.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationId;
import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;


    public void register() {

    }

    public void decline(RegistrationId registrationId) {

    }

    public void accept(RegistrationId registrationId) {

    }


}
