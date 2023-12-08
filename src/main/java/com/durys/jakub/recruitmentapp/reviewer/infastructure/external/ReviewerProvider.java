package com.durys.jakub.recruitmentapp.reviewer.infastructure.external;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Component
public class ReviewerProvider {

    private final WebClient webClient;

    ReviewerProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Reviewer> all() {
        return Collections.emptyList(); //todo weblclient;
    }
}
