package com.durys.jakub.recruitmentapp.invitation.application.event;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.invitation.domain.Invitation;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationFactory;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
class InvitationSentEventHandler implements EventHandler<InterviewEvent.InvitationSent> {

    private final InvitationRepository invitationRepository;

    InvitationSentEventHandler(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    @Transactional
    public void handle(InterviewEvent.InvitationSent event) {

        Invitation invitation = InvitationFactory.create(event.interviewId(), event.reviewerId(), event.availableTerms());

        invitationRepository.save(invitation);
    }
}
