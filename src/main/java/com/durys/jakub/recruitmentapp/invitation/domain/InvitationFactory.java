package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvitationFactory {


    public static Invitation create(UUID interviewId, ReviewerId reviewerId, List<AvailableTerm> availableTerms) {
        return new Invitation(new Interview.Id(interviewId), reviewerId, new AvailableTerms(availableTerms));
    }
}
