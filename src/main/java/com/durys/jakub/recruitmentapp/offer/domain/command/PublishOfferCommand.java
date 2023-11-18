package com.durys.jakub.recruitmentapp.offer.domain.command;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;

public record PublishOfferCommand(Offer.Id offerId) {
}
