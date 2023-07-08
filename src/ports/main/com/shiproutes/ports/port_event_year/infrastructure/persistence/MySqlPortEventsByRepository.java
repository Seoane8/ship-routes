package com.shiproutes.ports.port_event_year.infrastructure.persistence;

import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import com.shiproutes.ports.port_event_year.infrastructure.persistence.hibernate.HibernatePortEventsByYearEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlPortEventsByRepository
    extends HibernateRepository<HibernatePortEventsByYearEntity>
    implements PortEventsByYearRepository {

    public MySqlPortEventsByRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortEventsByYearEntity.class);
    }

    @Override
    public Set<PortEventsByYear> searchAll() {
        return all().stream().map(HibernatePortEventsByYearEntity::toEntity).collect(Collectors.toSet());
    }
}
