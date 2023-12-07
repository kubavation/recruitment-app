package com.durys.jakub.recruitmentapp.reviewer.application.event;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import com.durys.jakub.recruitmentapp.reviewer.domain.event.ReviewersChanged;
import org.springframework.stereotype.Component;

@Component
class ReviewersChangedEventHandler {

    private final ReviewerRepository repository;

    //todo provider

    ReviewersChangedEventHandler(ReviewerRepository repository) {
        this.repository = repository;
    }

    void handle(ReviewersChanged event) {
        //todo
    }
}
