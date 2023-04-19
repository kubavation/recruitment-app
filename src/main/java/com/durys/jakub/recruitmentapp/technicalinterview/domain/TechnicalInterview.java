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

    public TechnicalInterview accept(ReviewerId reviewerId, String opinion) {
        add(new Opinion(reviewerId, opinion, Opinion.Status.ACCEPTED));

        if (opinions.size() >= acceptanceLevel.val()) {
            //todo markAsAccepted
        }

        return this;
    }

    public TechnicalInterview decline(ReviewerId reviewerId, String opinion) {
        add(new Opinion(reviewerId, opinion, Opinion.Status.DECLINED));
        //todo markAsDeclined
        return this;
    }

    private void add(Opinion opinion) {
        opinions.add(opinion)
    }

}
