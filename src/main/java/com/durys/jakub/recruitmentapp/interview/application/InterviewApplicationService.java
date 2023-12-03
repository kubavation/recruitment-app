package com.durys.jakub.recruitmentapp.interview.application;

import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.InterviewRepository;
import com.durys.jakub.recruitmentapp.interview.domain.command.InterviewCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class InterviewApplicationService {

    private final InterviewRepository interviewRepository;

    InterviewApplicationService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Transactional
    public void handle(InterviewCommand.AssignReviewerCommand command) {

        Interview interview = interviewRepository.load(new Interview.Id(command.interviewId()));

        interview.assignReviewer(command.reviewerId(), command.at());

        interviewRepository.save(interview);
    }

    @Transactional
    public void handle(InterviewCommand.CompleteInterviewCommand command) {

        Interview interview = interviewRepository.load(new Interview.Id(command.interviewId()));

        interview.complete(command.opinion(), command.acceptation());

        interviewRepository.save(interview);
    }

    @Transactional
    public void handle(InterviewCommand.SendInterviewCommand command) {

        Interview interview = interviewRepository.load(new Interview.Id(command.interviewId()));

        interview.sendInvitationTo(command.reviewerId());

        interviewRepository.save(interview);
    }
}
