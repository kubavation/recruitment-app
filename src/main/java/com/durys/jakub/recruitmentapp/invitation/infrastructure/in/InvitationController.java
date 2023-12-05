package com.durys.jakub.recruitmentapp.invitation.infrastructure.in;

import com.durys.jakub.recruitmentapp.invitation.application.InvitationApplicationService;
import com.durys.jakub.recruitmentapp.invitation.domain.command.InvitationCommand;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/invitations")
class InvitationController {

    private final InvitationApplicationService invitationApplicationService;

    InvitationController(InvitationApplicationService invitationApplicationService) {
        this.invitationApplicationService = invitationApplicationService;
    }

    @PatchMapping("/{invitationId}/reject")
    void reject(@PathVariable UUID invitationId, @RequestParam String reason) {
        invitationApplicationService.handle(
            new InvitationCommand.RejectInvitationCommand(invitationId, reason)
        );
    }

    @PatchMapping("/{invitationId}/accept")
    void accept(@PathVariable UUID invitationId, @RequestParam LocalDateTime term) {
        invitationApplicationService.handle(
                new InvitationCommand.AcceptInvitationCommand(invitationId, term)
        );
    }
}
