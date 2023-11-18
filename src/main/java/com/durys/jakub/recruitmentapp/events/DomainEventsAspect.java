package com.durys.jakub.recruitmentapp.events;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
class DomainEventsAspect {

    private final EventEmitter events;

    @AfterReturning(value = "execution(* com.durys.jakub.recruitmentapp.ddd.DomainRepository.save(..))", returning = "root")
    public void publish(AggregateRoot root) {

        log.info("publishing domain events");

        root.domainEvents()
                .forEach(events::emit);

    }

}

