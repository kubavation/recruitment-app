package com.durys.jakub.recruitmentapp.offer.infrastructure.persistance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "OFFER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class OfferEntity {

    @Id
    private Long id;
    private UUID domainId;

    private String position;
    private String description;
    private Integer limit;
    private LocalDate from;
    private LocalDate to;
    private String state;

    public OfferEntity(String position, String description, Integer limit, LocalDate from, LocalDate to, String state) {
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.from = from;
        this.to = to;
        this.state = state;
    }
}
