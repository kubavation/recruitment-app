package com.durys.jakub.recruitmentapp.interview.application.event;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.InterviewRepository;
import com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
class InvitationAcceptedEventHandler implements EventHandler<InvitationEvent.InvitationAccepted> {

    private final InterviewRepository interviewRepository;

    InvitationAcceptedEventHandler(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    @Transactional
    public void handle(InvitationEvent.InvitationAccepted invitationAccepted) {

        Interview.Id interviewId = new Interview.Id(invitationAccepted.interviewId());

        Interview interview = interviewRepository.load(interviewId);

        interview.acceptInvitation(new ReviewerId(invitationAccepted.reviewerId()), invitationAccepted.term());

        interviewRepository.save(interview);
    }
}
