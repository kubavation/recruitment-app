package com.durys.jakub.recruitmentapp.offer.domain.command;

import com.durys.jakub.recruitmentapp.cqrs.Command;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CloseOfferCommand(Offer.Id offerId, LocalDateTime at) implements Command {
}
