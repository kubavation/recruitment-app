package com.durys.jakub.recruitmentapp.export.statisticts.registration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnExpression("${export.registration-statistics.enabled:false}")
class RegistrationStatisticsScheduler {


    @Scheduled(cron = "${export.registration-statistics.cron}")
    void export() {
        log.info("registration statistics export - start");
    }

}
