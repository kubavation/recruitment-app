package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.ddd.DomainRepository;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;

import java.util.Set;

public interface InvitationRepository extends DomainRepository<Invitation> {
    Invitation load(Invitation.Id id);
    Set<Invitation> loadBy(Interview.Id interviewId);
}
