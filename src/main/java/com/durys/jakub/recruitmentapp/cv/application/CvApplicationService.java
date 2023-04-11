package com.durys.jakub.recruitmentapp.cv.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationAccepted;
import org.springframework.context.event.EventListener;

@ApplicationService
public class CvApplicationService {


    @EventListener
    public void onAcceptedRegistration(RegistrationAccepted event) {
        //todo
    }
}
