package com.durys.jakub.recruitmentapp.reviewer.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

public interface ReviewerRepository {
    Flux<Reviewer> load();
    Mono<Reviewer> load(ReviewerId reviewerId);
}
