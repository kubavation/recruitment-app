package com.durys.jakub.recruitmentapp.invitation.domain.command;

import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface InvitationCommand {

    record AcceptInvitationCommand(UUID invitationId, LocalDateTime at) implements InvitationCommand {}
    record RejectInvitationCommand(UUID invitationId, String reason) implements InvitationCommand {}
}
