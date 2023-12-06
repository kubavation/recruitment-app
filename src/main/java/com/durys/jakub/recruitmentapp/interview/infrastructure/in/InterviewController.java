package com.durys.jakub.recruitmentapp.interview.infrastructure.in;

import com.durys.jakub.recruitmentapp.interview.application.InterviewApplicationService;
import com.durys.jakub.recruitmentapp.interview.domain.command.InterviewCommand;
import com.durys.jakub.recruitmentapp.invitation.application.InvitationApplicationService;
import com.durys.jakub.recruitmentapp.invitation.domain.command.InvitationCommand;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
}
