package ru.vlapin.demo.paveldemo;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PavelDemoApplication {

  public PavelDemoApplication() {
    System.out.println("1 = " + 1);
  }

  public static void main(String[] args) {
    SpringApplication.run(PavelDemoApplication.class, args);
    //    Arrays.stream("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    //                      .split(" "))
    //        .max((o1, o2) ->
    //                 (int) (o1.chars().filter(value -> (char) value == 'o').count() - o2.chars().filter(value -> (char) value == 'o').count()))
    //        .ifPresent(System.out::println);
  }

  @Bean
  Integer integer() {
    return 555;
  }

  @PostConstruct
  private void init() {
    System.out.println("2 = " + 2);
  }

  @EventListener
  public void afterContextInitialization(ContextRefreshedEvent __) {
    System.out.println("3 = " + 3);
  }

}
