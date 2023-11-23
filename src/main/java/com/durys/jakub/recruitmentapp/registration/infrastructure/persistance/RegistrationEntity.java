package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.cv.Cv;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "OFFER_REGISTRATION")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
@AllArgsConstructor
public class RegistrationEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OFFER_ID")
    private OfferEntity offer;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "APPLICANT_FIRST_NAME")),
        @AttributeOverride(name = "lastName", column = @Column(name = "APPLICANT_LAST_NAME")),
        @AttributeOverride(name = "email", column = @Column(name = "APPLICANT_EMAIL")),
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "APPLICANT_PHONE_NUMBER"))
    })
    private ApplicantInformation applicant;

    private String status;

    @Column(name = "REJECTION_REASON")
    private String rejectionReason;

    @OneToOne
    @JoinColumn(name = "CV_ID")
    private Cv cv;

    RegistrationEntity(UUID id, OfferEntity offer, ApplicantInformation applicant,
                       String rejectionReason, Cv cv, String status) {
        this.id = id;
        this.offer = offer;
        this.applicant = applicant;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.cv = cv;
    }
}
