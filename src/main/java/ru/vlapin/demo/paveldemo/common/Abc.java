package ru.vlapin.demo.paveldemo.common;

import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Abc implements InjecterInterface {

  @NonFinal
  String s;

  @PostConstruct
  private void init() {
    System.out.println("s = " + s);
  }

  @Override
  public void setS(String string) {
    s = string;
  }
}
