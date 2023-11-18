package com.durys.jakub.recruitmentapp.cv.application;

import com.durys.jakub.recruitmentapp.cv.domain.Cv;
import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.cv.domain.CvRepository;
import com.durys.jakub.recruitmentapp.ddd.ApplicationService;
import com.durys.jakub.recruitmentapp.external.mail.MailClient;
import com.durys.jakub.recruitmentapp.external.mail.MailFactory;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationAccepted;
import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class CvApplicationService {

    private final CvRepository cvRepository;
    private final ReviewerRepository reviewerRepository;
    private final MailClient mailClient;

    @EventListener
    public void onAcceptedRegistration(RegistrationAccepted event) {

        Cv cv = new Cv(new CvId(UUID.randomUUID()), event.registrationId());
        cvRepository.save(cv);

        reviewerRepository.load()
                .map(Reviewer::email)
                .map(MailFactory::instance)
                .subscribe(mailClient::send);
    }
}
