package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.interview.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InterviewTest {

    @Test
    void shouldCreateInterview() {

        Interview interview = new Interview(
                new Registration.Id(UUID.randomUUID()), new Offer.Id(UUID.randomUUID()), new TenantId(UUID.randomUUID()));

        assertEquals(Interview.State.New, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InterviewInitialized));
    }

    @Test
    void shouldAssignReviewer() {

        Interview interview = addInterview("New");

        interview.assignReviewer(new ReviewerId(UUID.randomUUID()), LocalDate.of(2023, 12, 12).atTime(15, 0));

        assertEquals(Interview.State.Waiting, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.ReviewerAssigned));
    }

    @Test
    void shouldCompleteInterview() {

        Interview interview = addInterview("Waiting");
        interview.assignReviewer(new ReviewerId(UUID.randomUUID()), LocalDate.of(2023, 12, 12).atTime(15, 0));

        interview.complete("Opinion", true);

        assertEquals(Interview.State.Completed, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InterviewCompleted));
    }


    private Interview addInterview(String state) {
        return InterviewFactory.create(
            UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            UUID.randomUUID(), new TenantId(UUID.randomUUID()), state);
    }

}