package com.durys.jakub.recruitmentapp.technicalinterview.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class TechnicalInterviewApplicationService {

    private final TechnicalInterviewRepository interviewRepository;

}
