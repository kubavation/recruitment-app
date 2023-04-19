package com.durys.jakub.recruitmentapp.technicalinterview.domain.event;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewId;

public record TechnicalInterviewDeclined(TechnicalInterviewId technicalInterviewId) implements DomainEvent { }
