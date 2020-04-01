package ru.vlapin.experiments.springdatajpademo2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.vlapin.experiments.springdatajpademo2.dao.FlightRepository;
import ru.vlapin.experiments.springdatajpademo2.model.Flight;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataJpaExtensionMethods {

  FlightRepository flightRepository;

  @Test
  @SneakyThrows
  @DisplayName("extension methods works correctly")
  void extensionMethodsWorksCorrectlyTest() {
    int count = (int) flightRepository.count();

    Flight flight1 = Flight.builder().build();
    Flight flight2 = Flight.builder().origin("Tokyo").build();
    Flight flight3 = Flight.builder().build();
    Flight flight4 = Flight.builder().build();

    Stream.of(flight1, flight2, flight3, flight4)
        .forEach(flightRepository::save);

    assertThat(flightRepository.findByOrigin("Tokyo"))
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(flight2);

    assertThat(flightRepository.findAll())
        .hasSize(count + 4)
        .last()
        .isEqualToComparingFieldByField(flight4);

  }
}
