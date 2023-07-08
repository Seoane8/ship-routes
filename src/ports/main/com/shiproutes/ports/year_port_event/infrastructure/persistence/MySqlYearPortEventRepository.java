package com.shiproutes.ports.year_port_event.infrastructure.persistence;

import com.shiproutes.ports.year_port_event.domain.YearPortEvent;
import com.shiproutes.ports.year_port_event.domain.YearPortEventRepository;
import com.shiproutes.ports.year_port_event.infrastructure.persistence.hibernate.HibernateYearPortEventEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlYearPortEventRepository
    extends HibernateRepository<HibernateYearPortEventEntity>
    implements YearPortEventRepository {

    public MySqlYearPortEventRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateYearPortEventEntity.class);
    }

    @Override
    public Set<YearPortEvent> searchAll() {
        return all().stream().map(HibernateYearPortEventEntity::toEntity).collect(Collectors.toSet());
    }
}
