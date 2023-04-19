package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class TechnicalInterview {

    private final TechnicalInterviewId technicalInterviewId;
    private final RegistrationId registrationId;
    private final InterviewAcceptanceLevel acceptanceLevel;
    private final Set<Opinion> opinions = new HashSet<>();

    public void accept(ReviewerId reviewerId, String opinion) {
        opinions.add(new Opinion(reviewerId, opinion, Opinion.Status.ACCEPTED));
    }

    public void decline(ReviewerId reviewerId, String opinion) {
        opinions.add(new Opinion(reviewerId, opinion, Opinion.Status.DECLINED));
    }
    
}
