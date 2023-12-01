package com.durys.jakub.recruitmentapp.invitation.application;

import com.durys.jakub.recruitmentapp.invitation.domain.Invitation;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;
import com.durys.jakub.recruitmentapp.invitation.domain.command.InvitationCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class InvitationApplicationService {

    private final InvitationRepository invitationRepository;

    InvitationApplicationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Transactional
    public void handle(InvitationCommand.AcceptInvitationCommand command) {

        Invitation invitation = invitationRepository.load(new Invitation.Id(command.invitationId()));

        invitation.accept(command.at());

        invitationRepository.save(invitation);
    }

    @Transactional
    public void handle(InvitationCommand.RejectInvitationCommand command) {

        Invitation invitation = invitationRepository.load(new Invitation.Id(command.invitationId()));

        invitation.reject(command.reason());

        invitationRepository.save(invitation);
    }
}
