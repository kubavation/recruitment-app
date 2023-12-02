package com.durys.jakub.recruitmentapp.interview.domain.command;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface InvitationCommand {

    record AssignReviewerCommand(UUID interviewId, ReviewerId reviewerId, LocalDateTime at) implements InvitationCommand {}
    record SendInvitationCommand(UUID interviewId, ReviewerId reviewerId) implements InvitationCommand {}
    record CompleteInvitationCommand(UUID interviewId, String opinion, boolean acceptation) implements InvitationCommand {}
}
