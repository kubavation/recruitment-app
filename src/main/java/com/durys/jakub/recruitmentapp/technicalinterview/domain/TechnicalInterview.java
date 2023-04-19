package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEventRegistry;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.event.TechnicalInterviewAccepted;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.event.TechnicalInterviewDeclined;
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

        if (accepted()) {
            DomainEventRegistry
                    .instance()
                    .publish(new TechnicalInterviewAccepted(technicalInterviewId));
        }

        return this;
    }

    public TechnicalInterview decline(ReviewerId reviewerId, String opinion) {

        add(new Opinion(reviewerId, opinion, Opinion.Status.DECLINED));

        DomainEventRegistry
                .instance()
                .publish(new TechnicalInterviewDeclined(technicalInterviewId));

        return this;
    }

    private void add(Opinion opinion) {
        opinions.add(opinion);
    }

    private boolean accepted() {
        return opinions.size() >= acceptanceLevel.val();
    }

}
