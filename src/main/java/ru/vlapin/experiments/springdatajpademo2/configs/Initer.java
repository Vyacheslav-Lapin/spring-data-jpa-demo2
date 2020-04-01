package ru.vlapin.experiments.springdatajpademo2.configs;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.vlapin.experiments.springdatajpademo2.dao.FlightRepository;
import ru.vlapin.experiments.springdatajpademo2.model.Flight;

@Component
@AllArgsConstructor
public class Initer implements ApplicationRunner {

  FlightRepository flightRepository;

  @Override
  public void run(ApplicationArguments __) {
    Flight flight1 = Flight.builder().build();
    Flight flight2 = Flight.builder().origin("Tokyo").build();
    Flight flight3 = Flight.builder().build();
    Flight flight4 = Flight.builder().build();

    Stream.of(flight1, flight2, flight3, flight4)
        .forEach(flightRepository::save);
  }
}
