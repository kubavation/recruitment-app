package com.durys.jakub.recruitmentapp.invitation.application.event;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.invitation.domain.Invitation;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class InterviewClosedEventHandler implements EventHandler<InterviewEvent.ReviewerAssigned> {

    private final InvitationRepository invitationRepository;

    InterviewClosedEventHandler(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    public void handle(InterviewEvent.ReviewerAssigned event) {

        Set<Invitation> invitations = invitationRepository.loadBy(new Interview.Id(event.interviewId()));

        //todo closing invitations

    }
}
