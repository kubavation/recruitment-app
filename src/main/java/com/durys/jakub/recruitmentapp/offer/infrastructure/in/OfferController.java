package com.durys.jakub.recruitmentapp.offer.infrastructure.in;

import com.durys.jakub.recruitmentapp.offer.application.OfferApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.command.AddOfferCommand;
import com.durys.jakub.recruitmentapp.offer.domain.command.CloseOfferCommand;
import com.durys.jakub.recruitmentapp.offer.domain.command.PublishOfferCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/offers")
class OfferController {

    private final OfferApplicationService offerApplicationService;

    OfferController(OfferApplicationService offerApplicationService) {
        this.offerApplicationService = offerApplicationService;
    }

    @PostMapping
    void addOffer(@RequestBody AddOfferDTO offer) {
        offerApplicationService.handle(
            new AddOfferCommand(
                    offer.getPosition(), offer.getDescription(), offer.getLimit(), offer.getFrom(), offer.getTo())
        );
    }

    @PatchMapping("/{offerId}/publish")
    void publishOffer(@PathVariable UUID offerId) {
        offerApplicationService.handle(
                new PublishOfferCommand(new Offer.Id(offerId))
        );
    }

    @PatchMapping("/{offerId}/close")
    void closeOffer(@PathVariable UUID offerId,
                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime closedAt) {
        offerApplicationService.handle(
                new CloseOfferCommand(new Offer.Id(offerId), closedAt)
        );
    }

}
