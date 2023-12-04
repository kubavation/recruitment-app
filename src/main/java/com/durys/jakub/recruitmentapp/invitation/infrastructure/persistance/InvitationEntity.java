package com.durys.jakub.recruitmentapp.invitation.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.interview.infrastructure.persistance.InterviewEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "INTERVIEW_INVITATION")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
public class InvitationEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "INTERVIEW_ID")
    private InterviewEntity interview;

    @Column(name = "REVIEWER_ID")
    private UUID reviewerId;

    private LocalDateTime at;

    private String state;

    private String reason;

    InvitationEntity(UUID id, InterviewEntity interview, UUID reviewerId,
                     LocalDateTime at, String state, String reason) {
        this.id = id;
        this.interview = interview;
        this.reviewerId = reviewerId;
        this.at = at;
        this.state = state;
        this.reason = reason;
    }
}


