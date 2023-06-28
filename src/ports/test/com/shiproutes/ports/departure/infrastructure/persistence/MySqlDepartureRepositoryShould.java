package com.shiproutes.ports.departure.infrastructure.persistence;

import com.shiproutes.ports.departure.DepartureModuleInfrastructureTestCase;
import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureIdMother;
import com.shiproutes.ports.departure.domain.DepartureMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySqlDepartureRepositoryShould extends DepartureModuleInfrastructureTestCase {

    @Test
    void save_a_departure() {
        mySqlDepartureRepository.save(DepartureMother.random());
    }

    @Test
    void return_an_existent_departure() {
        Departure departure = DepartureMother.random();
        mySqlDepartureRepository.save(departure);

        Optional<Departure> departureFound = mySqlDepartureRepository.search(departure.id());
        assertEquals(Optional.of(departure), departureFound);
    }

    @Test
    void not_return_a_non_existent_departure() {
        assertEquals(Optional.empty(), mySqlDepartureRepository.search(DepartureIdMother.random()));
    }

}
