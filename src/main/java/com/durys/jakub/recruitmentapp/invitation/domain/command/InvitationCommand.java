package com.durys.jakub.recruitmentapp.invitation.domain.command;

import java.time.LocalDateTime;

public sealed interface InvitationCommand {

    record AcceptInvitationCommand(LocalDateTime at) implements InvitationCommand {}
    record RejectInvitationCommand(String reason) implements InvitationCommand {}
}
