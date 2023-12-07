package com.durys.jakub.recruitmentapp.invitation.application.event;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
class InterviewClosedEventHandler implements EventHandler<InterviewEvent.ReviewerAssigned> {

    private final InvitationRepository invitationRepository;

    InterviewClosedEventHandler(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    @Transactional
    public void handle(InterviewEvent.ReviewerAssigned event) {

        invitationRepository.loadBy(new Interview.Id(event.interviewId()))
                .stream()
                .forEach(invitation -> {
                    invitation.close();
                    invitationRepository.save(invitation);
                });
    }

}
