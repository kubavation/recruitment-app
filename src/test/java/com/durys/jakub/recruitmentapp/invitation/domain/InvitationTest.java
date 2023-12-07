package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.reviewer.ReviewerId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {

    @Test
    void shouldCreateInvitation() {

        var interviewId = new Interview.Id(UUID.randomUUID());
        var reviewerId = new ReviewerId(UUID.randomUUID());
        var availableTerms = new AvailableTerms(
                List.of(
                    new AvailableTerm(LocalDate.of(2023, 10, 10), LocalTime.of(9, 0), LocalTime.of(10, 0))
                ));

        var invitation = new Invitation(interviewId, reviewerId, availableTerms);

        assertNotNull(invitation.id());
        assertTrue(invitation.domainEvents().stream().anyMatch(event -> event instanceof InvitationEvent.InvitationReceived));
    }

    @Test
    void shouldAcceptInvitation() {

        var interviewId = new Interview.Id(UUID.randomUUID());
        var reviewerId = new ReviewerId(UUID.randomUUID());
        var availableTerms = new AvailableTerms(
                List.of(
                        new AvailableTerm(LocalDate.of(2023, 10, 10), LocalTime.of(9, 0), LocalTime.of(10, 0))
                ));
        var invitation = new Invitation(interviewId, reviewerId, availableTerms);
        var at = LocalDate.of(2023, 10, 10).atTime(9, 30);

        invitation.accept(at);

        assertEquals(Invitation.State.Closed, invitation.state());
        assertTrue(invitation.domainEvents().stream().anyMatch(event -> event instanceof InvitationEvent.InvitationAccepted));
    }

    @Test
    void shouldRejectInvitation() {

        var interviewId = new Interview.Id(UUID.randomUUID());
        var reviewerId = new ReviewerId(UUID.randomUUID());
        var availableTerms = new AvailableTerms(
                List.of(
                        new AvailableTerm(LocalDate.of(2023, 10, 10), LocalTime.of(9, 0), LocalTime.of(10, 0))
                ));
        var invitation = new Invitation(interviewId, reviewerId, availableTerms);

        invitation.reject("Im on holiday");

        assertEquals(Invitation.State.Closed, invitation.state());
        assertTrue(invitation.domainEvents().stream().anyMatch(event -> event instanceof InvitationEvent.InvitationRejected));
    }

}