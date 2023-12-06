package com.durys.jakub.recruitmentapp.invitation.infrastructure;

import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.invitation.domain.Invitation;
import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryInvitationRepository implements InvitationRepository {

    private static Map<Invitation.Id, Invitation> DB = new ConcurrentHashMap<>();

    @Override
    public void save(Invitation aggregate) {
        DB.put(aggregate.id(), aggregate);
    }

    @Override
    public Invitation load(Invitation.Id id) {
        return DB.get(id);
    }

    @Override
    public Set<Invitation> loadBy(Interview.Id interviewId) {
        return DB.values().stream()
                .filter(invitation -> invitation.interviewId().equals(interviewId))
                .collect(Collectors.toSet());
    }
}
