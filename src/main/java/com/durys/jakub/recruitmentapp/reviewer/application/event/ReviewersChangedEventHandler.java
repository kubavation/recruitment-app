package com.durys.jakub.recruitmentapp.reviewer.application.event;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import com.durys.jakub.recruitmentapp.reviewer.domain.event.ReviewersChanged;
import com.durys.jakub.recruitmentapp.reviewer.infastructure.external.ReviewerProvider;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ReviewersChangedEventHandler {

    private final ReviewerRepository repository;
    private final ReviewerProvider reviewerProvider;

    ReviewersChangedEventHandler(ReviewerRepository repository, ReviewerProvider reviewerProvider) {
        this.repository = repository;
        this.reviewerProvider = reviewerProvider;
    }

    @Transactional
    public void handle(ReviewersChanged event) {
        List<Reviewer> reviewers = reviewerProvider.all();
        repository.update(reviewers);
    }
}
