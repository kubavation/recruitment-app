package com.durys.jakub.recruitmentapp.interview.infrastructure.in;

import com.durys.jakub.recruitmentapp.interview.application.InterviewApplicationService;
import com.durys.jakub.recruitmentapp.interview.domain.command.InterviewCommand;
import com.durys.jakub.recruitmentapp.reviewer.ReviewerId;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/interviews")
class InterviewController {

    private final InterviewApplicationService interviewApplicationService;

    InterviewController(InterviewApplicationService interviewApplicationService) {
        this.interviewApplicationService = interviewApplicationService;
    }

    @PatchMapping("/{interviewId}/complete")
    void complete(@PathVariable UUID interviewId, @RequestBody CompleteInterviewDTO dto) {
        interviewApplicationService.handle(
            new InterviewCommand.CompleteInterviewCommand(interviewId, dto.opinion(), dto.acceptation())
        );
    }

    @PatchMapping("/{interviewId}/reviewers")
    void assignReviewer(@PathVariable UUID interviewId, @RequestParam UUID reviewerId, @RequestParam LocalDateTime at) {
        interviewApplicationService.handle(
                new InterviewCommand.AssignReviewerCommand(interviewId, new ReviewerId(reviewerId), at)
        );
    }

    @PostMapping("/{interviewId}/invitations")
    void sendInvitations(@PathVariable UUID interviewId, @RequestBody Set<UUID> reviewersId) {

        reviewersId
            .forEach(reviewerId -> interviewApplicationService.handle(
                 new InterviewCommand.SendInvitationCommand(interviewId, new ReviewerId(reviewerId))
            ));
    }
}
