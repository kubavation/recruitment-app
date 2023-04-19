package com.durys.jakub.recruitmentapp.reviewer.domain;

import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterview;

public record Reviewer(ReviewerId reviewerId, String name, String email) {

    public void leavePositiveOpinion(TechnicalInterview interview, String opinion) {
        interview.accept(reviewerId, opinion);
    }

    public void leaveNegativeOpinion(TechnicalInterview interview, String opinion) {
        interview.decline(reviewerId, opinion);
    }

}
