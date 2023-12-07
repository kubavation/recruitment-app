package com.durys.jakub.recruitmentapp.interview.application.event;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.InterviewRepository;
import com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
class InvitationRejectedEventHandler implements EventHandler<InvitationEvent.InvitationRejected> {

    private final InterviewRepository interviewRepository;

    InvitationRejectedEventHandler(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    @Transactional
    public void handle(InvitationEvent.InvitationRejected invitationRejected) {

        Interview.Id interviewId = new Interview.Id(invitationRejected.interviewId());

        Interview interview = interviewRepository.load(interviewId);

        interview.declineInvitation();

        interviewRepository.save(interview);
    }
}
