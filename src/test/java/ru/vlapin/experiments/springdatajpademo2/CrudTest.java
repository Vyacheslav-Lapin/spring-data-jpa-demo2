package ru.vlapin.experiments.springdatajpademo2;

import static org.assertj.core.api.Assertions.assertThat;

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
public class CrudTest {

  FlightRepository flightRepository;

  @Test
  @SneakyThrows
  @DisplayName("crud works correctly")
  void crudWorksCorrectlyTest() {
    int count = (int) flightRepository.count();

    Flight flight = Flight.builder().build();

    flightRepository.save(flight);

    assertThat(flightRepository.findAll())
        .hasSize(count + 1)
        .last()
        .isEqualToComparingFieldByField(flight);
  }
}
