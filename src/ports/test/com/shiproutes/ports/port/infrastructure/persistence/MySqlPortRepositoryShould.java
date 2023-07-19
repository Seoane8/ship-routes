package com.shiproutes.ports.port.infrastructure.persistence;

import com.shiproutes.ports.port.PortModuleInfrastructureTestCase;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortMother;
import com.shiproutes.shared.domain.PortIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class MySqlPortRepositoryShould extends PortModuleInfrastructureTestCase {

    @Test
    void save_a_port() {
        mySqlPortRepository.save(PortMother.random());
    }

    @Test
    void return_an_existent_port() {
        Port port = PortMother.random();
        mySqlPortRepository.save(port);

        Optional<Port> portFound = mySqlPortRepository.search(port.id());
        assertEquals(Optional.of(port), portFound);
    }

    @Test
    void not_return_a_non_existent_port() {
        assertEquals(Optional.empty(), mySqlPortRepository.search(PortIdMother.random()));
    }

    @Test
    void return_all_existent_ports() {
        Port firstPort = PortMother.random();
        Port secondPort = PortMother.random();
        mySqlPortRepository.save(firstPort);
        mySqlPortRepository.save(secondPort);

        assertEquals(Set.of(firstPort, secondPort), mySqlPortRepository.searchAll());
    }

    @Test
    void return_empty_list_when_no_port_exist() {
        assertEquals(Collections.emptySet(), mySqlPortRepository.searchAll());
    }
}
