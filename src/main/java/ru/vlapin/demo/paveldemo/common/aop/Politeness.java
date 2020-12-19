package ru.vlapin.demo.paveldemo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Politeness {

  @Pointcut("execution(Drink getSquishee(Person))")
  void pointcut() {
  }

  @Before("pointcut() && args(person)")
  void greetings(Person person) {
    log.info("Hi, {}, what's up?", person.name());
  }

  @AfterReturning(pointcut = "pointcut()", returning = "drink")
  void askOpinion(Drink drink) {
    log.info("Is {} good enough?", drink.name());
  }

  @AfterThrowing(pointcut="pointcut()", throwing = "ex")
  void sayYouAreNotAllowed(CustomerBrokeException ex) {
    log.warn("Hmmmmmmm...");
  }

  @After("pointcut() && args(person)")
  void goodBye(Person person) {
    log.info("Good bye, {}!", person.name());
  }
}
