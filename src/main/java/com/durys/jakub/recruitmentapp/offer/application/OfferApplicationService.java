package com.durys.jakub.recruitmentapp.offer.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class OfferApplicationService {

    private final OfferRepository offerRepository;


}
