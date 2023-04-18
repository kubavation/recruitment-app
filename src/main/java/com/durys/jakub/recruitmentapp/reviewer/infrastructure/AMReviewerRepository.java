package com.durys.jakub.recruitmentapp.reviewer.infrastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class AMReviewerRepository implements ReviewerRepository {

    private final WebClient webClient;

    @Override
    public Collection<Reviewer> load() {
        return Collections.emptySet();
    }

    @Override
    public Optional<Reviewer> load(ReviewerId reviewerId) {
        return Optional.empty();
    }
}
