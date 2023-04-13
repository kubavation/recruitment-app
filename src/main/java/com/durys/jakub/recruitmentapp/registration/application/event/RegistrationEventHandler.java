package com.durys.jakub.recruitmentapp.registration.application.event;

import com.durys.jakub.recruitmentapp.cv.domain.events.CvAccepted;
import com.durys.jakub.recruitmentapp.cv.domain.events.CvDeclined;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventHandler {


    @EventListener
    public void onCvAccepted(CvAccepted event) {

    }

    @EventListener
    public void onCvDeclined(CvDeclined event) {

    }
}
