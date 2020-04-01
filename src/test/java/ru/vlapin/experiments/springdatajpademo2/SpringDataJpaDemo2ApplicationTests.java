package ru.vlapin.experiments.springdatajpademo2;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vlapin.experiments.springdatajpademo2.model.Flight;

//@Transactional
//@SpringBootTest
@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SpringDataJpaDemo2ApplicationTests {

  EntityManager entityManager;

  @Test
  void contextLoads() {
  }

  @Test
  @SneakyThrows
  @DisplayName("Entity should work in DB")
  void entityShouldWorkInDBTest() {
    int count = entityManager.createQuery("select count(f) from Flight f", Long.class)
                    .getSingleResult()
                    .intValue();

    val flight = Flight.builder().build();

    //    final Date/*val*/ date = new Date() {
    //      public String m2() {
    //        return "kaghsdfkjhsd";
    //      }
    //    };

    //    date.m2();

    entityManager.persist(flight);
    entityManager.flush();

    assertThat(entityManager.createQuery("select  f from Flight f", Flight.class).getResultList())
        .hasSize(count + 1)
        .last()
        .isEqualToComparingFieldByField(flight);
  }
}
