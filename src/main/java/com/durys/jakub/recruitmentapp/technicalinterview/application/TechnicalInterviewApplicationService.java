package com.durys.jakub.recruitmentapp.technicalinterview.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterview;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewId;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class TechnicalInterviewApplicationService {

    private final TechnicalInterviewRepository interviewRepository;
    private final ReviewerRepository reviewerRepository;

    public Mono<Void> addPositiveOpinion(UUID interviewId, UUID reviewerId, String opinion) {

        return interviewRepository
                    .load(new TechnicalInterviewId(interviewId))
                        .zipWith(reviewerRepository.load(new ReviewerId(reviewerId)))
                        .flatMap(t -> t.getT2().leavePositiveOpinion(t.getT1(), opinion))
                        .flatMap(interviewRepository::save);
    }

    public Mono<Void> addNegativeOpinion(UUID interviewId, UUID reviewerId, String opinion) {

        return interviewRepository
                .load(new TechnicalInterviewId(interviewId))
                .zipWith(reviewerRepository.load(new ReviewerId(reviewerId)))
                .flatMap(t -> t.getT2().leaveNegativeOpinion(t.getT1(), opinion))
                .flatMap(interviewRepository::save);
    }

}
