package com.shiproutes.ports.arrival.infrastructure.persistence;

import com.shiproutes.ports.arrival.ArrivalModuleInfrastructureTestCase;
import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalIdMother;
import com.shiproutes.ports.arrival.domain.ArrivalMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlArrivalRepositoryShould extends ArrivalModuleInfrastructureTestCase {

    @Test
    void save_a_arrival() {
        mySqlArrivalRepository.save(ArrivalMother.random());
    }

    @Test
    void return_an_existent_arrival() {
        Arrival arrival = ArrivalMother.random();
        mySqlArrivalRepository.save(arrival);

        Optional<Arrival> arrivalFound = mySqlArrivalRepository.search(arrival.id());
        assertEquals(Optional.of(arrival), arrivalFound);
    }

    @Test
    void not_return_a_non_existent_arrival() {
        assertEquals(Optional.empty(), mySqlArrivalRepository.search(ArrivalIdMother.random()));
    }

}
