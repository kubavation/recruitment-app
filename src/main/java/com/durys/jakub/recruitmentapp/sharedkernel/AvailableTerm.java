package com.durys.jakub.recruitmentapp.sharedkernel;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public record AvailableTerm(LocalDate date, LocalTime from, LocalTime to) {

     public AvailableTerm {

        if (Objects.isNull(date)) {
            throw new ValidationException("Date cannot be empty");
        }

        if (Objects.isNull(from) || Objects.isNull(to)) {
            throw new ValidationException("Time from/to cannot be empty");
        }

        if (from.isAfter(to)) {
            throw new ValidationException("Time from cannot be later than time to");
        }

    }

    public boolean inRange(LocalDateTime at) {
        if (at.toLocalDate().equals(date)) {
            return !at.toLocalTime().isBefore(from) && !at.toLocalTime().isAfter(to);
        }
        return false;
    }
}
