package com.durys.jakub.recruitmentapp.offer.infrastructure.persistance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "OFFER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
public class OfferEntity {

    @Id
    private UUID id;

    private String position;
    private String description;
    private Integer limit;
    private LocalDate from;
    private LocalDate to;
    private String state;

    @Column(name = "CLOSED_AT")
    private LocalDateTime closedAt;

    public OfferEntity(UUID id, String position, String description, Integer limit, LocalDate from, LocalDate to, String state) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.from = from;
        this.to = to;
        this.state = state;
    }
}
