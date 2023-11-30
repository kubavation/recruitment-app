package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.ddd.DomainRepository;

public interface InvitationRepository extends DomainRepository<Invitation> {
    Invitation load(Invitation.Id id);
}
