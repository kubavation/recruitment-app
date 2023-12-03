package com.durys.jakub.recruitmentapp.interview.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.registration.infrastructure.persistance.RegistrationEntity;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table(name = "INTERVIEW")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
@AllArgsConstructor
public class InterviewEntity {

    @Id
    private UUID id;

    @NaturalId
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "REGISTRATION_ID")
    private RegistrationEntity registration;

    @Column(name = "TENANT_ID")
    private UUID tenantId;

    private LocalDateTime at;

    @Column(name = "REVIEWER_ID")
    private UUID reviewerId;

    private String state;

    private String opinion;
    private boolean acceptation;

    @OneToMany(mappedBy = "interview")
    private Set<InterviewAvailableTermEntity> availableTerms = new HashSet<>();

    InterviewEntity(UUID id, String identifier, RegistrationEntity registration,
                    UUID tenantId, String state) {
        this.id = id;
        this.identifier = identifier;
        this.registration = registration;
        this.tenantId = tenantId;
        this.state = state;
    }

     void setAvailableTerms(Set<InterviewAvailableTermEntity> availableTerms) {
        this.availableTerms = availableTerms.stream()
                .map(term -> term.setInterview(this))
                .collect(Collectors.toSet());
    }
}
