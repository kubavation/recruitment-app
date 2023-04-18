package com.durys.jakub.recruitmentapp.reviewer.domain;

import com.durys.jakub.recruitmentapp.ddd.annotations.DomainService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@DomainService
public class ReviewersService {

    public Collection<Reviewer> loadReviewers() {
        return Collections.emptySet();
    }

    public Optional<Reviewer> load(ReviewerId reviewerId) {
        return Optional.empty();
    }
}
