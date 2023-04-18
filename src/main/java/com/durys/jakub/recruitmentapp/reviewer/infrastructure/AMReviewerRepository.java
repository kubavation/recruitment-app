package com.durys.jakub.recruitmentapp.reviewer.infrastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class AMReviewerRepository implements ReviewerRepository {

    private final WebClient client;

    @Override
    public Flux<Reviewer> load() {
        return Flux.just();
    }

    @Override
    public Mono<Reviewer> load(ReviewerId reviewerId) {
        return Mono.empty();
    }
}
