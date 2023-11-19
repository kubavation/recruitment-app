package com.durys.jakub.recruitmentapp.events;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
class DomainEventsAspect {

    private final EventEmitter events;

    @After(value = "execution(* com.durys.jakub.recruitmentapp.ddd.DomainRepository.save(..))")
    public void publish(JoinPoint joinPoint) {

        log.info("publishing domain events");

        var domainEvents = ((AggregateRoot) joinPoint.getArgs()[0]).domainEvents();

        domainEvents.forEach(events::emit);
    }

}

