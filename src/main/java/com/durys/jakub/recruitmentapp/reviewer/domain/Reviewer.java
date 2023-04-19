package com.durys.jakub.recruitmentapp.reviewer.domain;

import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterview;
import reactor.core.publisher.Mono;

public record Reviewer(ReviewerId reviewerId, String name, String email) {

    public Mono<TechnicalInterview> leavePositiveOpinion(TechnicalInterview interview, String opinion) {
        return Mono.just(interview.accept(reviewerId, opinion));
    }

    public Mono<TechnicalInterview> leaveNegativeOpinion(TechnicalInterview interview, String opinion) {
        return Mono.just(interview.decline(reviewerId, opinion));
    }

}
