package ru.vlapin.demo.paveldemo.common;

import org.springframework.beans.factory.annotation.Autowired;

public interface InjecterInterface {

  @Autowired
  default void setInteger(Integer integer) {
    setS(integer.toString());
  }

  void setS(String string);
}
