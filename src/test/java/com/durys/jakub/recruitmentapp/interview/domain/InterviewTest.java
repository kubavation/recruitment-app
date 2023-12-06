package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.InterviewFactory;
import com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InterviewTest {

    @Test
    void shouldCreateInterview() {

        Interview interview = new Interview(
                new Registration.Id(UUID.randomUUID()), new TenantId(UUID.randomUUID()));

        assertEquals(Interview.State.New, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InterviewInitialized));
    }

    @Test
    void shouldSetInterviewAvailableTermsAndChangeStateToWaiting() {

        Interview interview = new Interview(
                new Registration.Id(UUID.randomUUID()), new TenantId(UUID.randomUUID()));
        var availableTerms = List.of(
                new AvailableTerm(LocalDate.of(2023, 12, 12), LocalTime.of(8, 0), LocalTime.of(9, 0)),
                new AvailableTerm(LocalDate.of(2023, 12, 13), LocalTime.of(10, 0), LocalTime.of(12, 0))
        );

        interview.chooseAvailableTerms(availableTerms);

        assertEquals(Interview.State.Waiting, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InterviewTermsChosen));
    }

    @Test
    void shouldAssignReviewer() {

        Interview interview = addInterview("New");
        addAvailableTerms(interview);

        interview.assignReviewer(new ReviewerId(UUID.randomUUID()), LocalDate.of(2023, 12, 12).atTime(8, 30));

        assertEquals(Interview.State.Planned, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.ReviewerAssigned));
    }

    @Test
    void shouldNotAssignReviewer_whenTermIsNotValidWithAvailableTerms() {

        Interview interview = addInterview("New");
        addAvailableTerms(interview);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> interview.assignReviewer(new ReviewerId(UUID.randomUUID()),
                        LocalDate.of(2023, 12, 12).atTime(16, 30)));

        assertEquals("Chosen date not in range of available terms", exception.getMessage());
    }

    @Test
    void shouldCompleteInterview() {

        Interview interview = addInterview("New");
        addAvailableTerms(interview);
        var reviewerId = new ReviewerId(UUID.randomUUID());

        interview.sendInvitationTo(reviewerId);
        interview.acceptInvitation(reviewerId, LocalDateTime.now());

        interview.complete("Opinion", true);

        assertEquals(Interview.State.Completed, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InterviewCompleted));
    }

    @Test
    void shouldAcceptInterviewInvitation() {

        Interview interview = addInterview("New");
        var reviewerId = new ReviewerId(UUID.randomUUID());
        addAvailableTerms(interview);

        interview.sendInvitationTo(reviewerId);

        interview.acceptInvitation(reviewerId, LocalDateTime.now());

        assertEquals(Interview.State.Planned, interview.state());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InvitationAccepted));
    }

    @Test
    void shouldDeclineInterviewInvitation() {

        Interview interview = addInterview("New");
        addAvailableTerms(interview);

        interview.sendInvitationTo(new ReviewerId(UUID.randomUUID()));

        interview.declineInvitation();

        assertEquals(Interview.State.Waiting, interview.state());
        assertNull(interview.reviewerId());
        assertTrue(interview.domainEvents().stream().anyMatch(event -> event instanceof InterviewEvent.InvitationDeclined));
    }


    private Interview addInterview(String state) {
        return InterviewFactory.create(
            UUID.randomUUID(), UUID.randomUUID(),
            UUID.randomUUID(), new TenantId(UUID.randomUUID()), state);
    }


    private static void addAvailableTerms(Interview interview) {
        var availableTerms = List.of(
                new AvailableTerm(LocalDate.of(2023, 12, 12), LocalTime.of(8, 0), LocalTime.of(9, 0)),
                new AvailableTerm(LocalDate.of(2023, 12, 13), LocalTime.of(10, 0), LocalTime.of(12, 0))
        );
        interview.chooseAvailableTerms(availableTerms);
    }


}