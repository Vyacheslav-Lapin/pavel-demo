package ru.vlapin.demo.paveldemo.common.aop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ApuBarTest {

  Bar bar;

  @Test
  void getSquisheeTest() {
    assertThat(bar.getSquishee(new Person("Вася", false)))
        .isNotNull()
        .isInstanceOf(Drink.class)
        .isExactlyInstanceOf(Squishee.class)
        .extracting(Drink::name)
        .isEqualTo("squishee");
  }

  @Test
  void getSquisheeBrokeTest() {
    assertThrows(CustomerBrokeException.class,
        () -> bar.getSquishee(new Person("Вася", true)));
  }
}
