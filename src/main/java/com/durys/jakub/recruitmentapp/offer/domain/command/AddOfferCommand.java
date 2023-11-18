package com.durys.jakub.recruitmentapp.offer.domain.command;

import com.durys.jakub.recruitmentapp.cqrs.Command;

import java.time.LocalDate;

public record AddOfferCommand(String position, String description, Integer applicantLimit,
                       LocalDate from, LocalDate to) implements Command {
}
