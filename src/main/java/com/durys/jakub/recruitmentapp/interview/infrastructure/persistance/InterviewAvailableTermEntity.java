package com.durys.jakub.recruitmentapp.interview.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table(name = "INTERVIEW_AVAILABLE_TERM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
@AllArgsConstructor
public class InterviewAvailableTermEntity {

    @Id
    private UUID id;

    private LocalDate at;
    private LocalTime from;
    private LocalTime to;

    @ManyToOne
    @JoinColumn(name = "INTERVIEW_ID")
    private InterviewEntity interview;
}
