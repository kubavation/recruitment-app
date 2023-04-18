package com.durys.jakub.recruitmentapp.registration.application.event;

import com.durys.jakub.recruitmentapp.cv.domain.events.CvAccepted;
import com.durys.jakub.recruitmentapp.cv.domain.events.CvDeclined;
import com.durys.jakub.recruitmentapp.external.mail.Mail;
import com.durys.jakub.recruitmentapp.external.mail.MailClient;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationEventHandler {

    private final MailClient mailClient;
    private final RegistrationRepository registrationRepository;

    @EventListener
    public void onCvAccepted(CvAccepted event) {
        Registration registration = registrationRepository.load(event.registrationId());
        mailClient.send(new Mail(registration.applicantEmail(), "todo", "todo"));
    }

    @EventListener
    public void onCvDeclined(CvDeclined event) {
        Registration registration = registrationRepository.load(event.registrationId());
        mailClient.send(new Mail(registration.applicantEmail(), "todo", "todo"));
    }
}
