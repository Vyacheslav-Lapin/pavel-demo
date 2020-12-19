package ru.vlapin.demo.paveldemo.common.aop;

import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

  @Override
  public Drink getSquishee(Person person) {
    if (person.isBroke())
      throw new CustomerBrokeException();

    return new Squishee("squishee");
  }
}
