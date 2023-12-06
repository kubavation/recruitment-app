package com.durys.jakub.recruitmentapp.interview.domain.command;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface InterviewCommand {

    record AssignReviewerCommand(UUID interviewId, ReviewerId reviewerId, LocalDateTime at) implements InterviewCommand {}
    record SendInvitationCommand(UUID interviewId, ReviewerId reviewerId) implements InterviewCommand {}
    record CompleteInterviewCommand(UUID interviewId, String opinion, boolean acceptation) implements InterviewCommand {}
}
