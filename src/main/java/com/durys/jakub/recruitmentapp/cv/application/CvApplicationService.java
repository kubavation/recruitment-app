package com.durys.jakub.recruitmentapp.cv.application;

import com.durys.jakub.recruitmentapp.cv.domain.Cv;
import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.cv.domain.CvRepository;
import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationAccepted;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class CvApplicationService {

    private final CvRepository cvRepository;

    @EventListener
    public void onAcceptedRegistration(RegistrationAccepted event) {

        Cv cv = new Cv(new CvId(UUID.randomUUID()), event.registrationId());
        cvRepository.save(cv);

        //todo notification for reviewers
    }
}
