package com.shiproutes.ports.port_event_year.infrastructure.persistence;

import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.ports.port_event_year.infrastructure.persistence.hibernate.HibernatePortEventsByYearEntity;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlPortEventsByYearRepository
    extends HibernateRepository<HibernatePortEventsByYearEntity>
    implements PortEventsByYearRepository {

    public MySqlPortEventsByYearRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortEventsByYearEntity.class);
    }

    @Override
    public void save(PortEventsByYear portEventsByYear) {
        persist(new HibernatePortEventsByYearEntity(portEventsByYear));
    }

    @Override
    public Set<PortEventsByYear> searchAll() {
        return all().stream().map(HibernatePortEventsByYearEntity::toEntity).collect(Collectors.toSet());
    }

    @Override
    public Optional<PortEventsByYear> search(PortId portId, Year year) {
        String query = "SELECT p FROM portEventsByYear p WHERE p.portId = :portId AND p.year = :year";
        return sessionFactory.getCurrentSession()
            .createQuery(query, this.aggregateClass)
            .setParameter("portId", portId.value())
            .setParameter("year", year.value())
            .uniqueResultOptional()
            .map(HibernatePortEventsByYearEntity::toEntity);
    }
}
