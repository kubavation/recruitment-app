package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "OFFER_REGISTRATION")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
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

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE")
    private byte[] file;

    RegistrationEntity(UUID id, OfferEntity offer, ApplicantInformation applicant, String status,
                       String rejectionReason, String fileName, byte[] file) {
        this.id = id;
        this.offer = offer;
        this.applicant = applicant;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.fileName = fileName;
        this.file = file;
    }
}
