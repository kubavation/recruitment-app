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

    @Column(name = "APPLICANT_FIRST_NAME")
    private String applicantFirstName;

    @Column(name = "APPLICANT_LAST_NAME")
    private String applicantLastName;

    @Column(name = "APPLICANT_EMAIL")
    private String applicantEmail;

    @Column(name = "APPLICANT_PHONE_NUMBER")
    private String applicantPhoneNumber;

    private String status;

    @Column(name = "REJECTION_REASON")
    private String rejectionReason;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE")
    private byte[] file;

    RegistrationEntity(UUID id, OfferEntity offer, String applicantFirstName, String applicantLastName,
                       String applicantEmail, String applicantPhoneNumber, String status,
                       String rejectionReason, String fileName, byte[] file) {
        this.id = id;
        this.offer = offer;
        this.applicantFirstName = applicantFirstName;
        this.applicantLastName = applicantLastName;
        this.applicantEmail = applicantEmail;
        this.applicantPhoneNumber = applicantPhoneNumber;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.fileName = fileName;
        this.file = file;
    }
}
