package com.shiproutes.ports.port_event_month.infrastructure.persistence;

import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthRepository;
import com.shiproutes.ports.port_event_month.infrastructure.persistence.hibernate.HibernatePortEventsByMonthEntity;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
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
public class MySqlPortEventsByMonthRepository
    extends HibernateRepository<HibernatePortEventsByMonthEntity>
    implements PortEventsByMonthRepository {

    public MySqlPortEventsByMonthRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortEventsByMonthEntity.class);
    }

    @Override
    public void save(PortEventsByMonth portEventsByYear) {
        persist(new HibernatePortEventsByMonthEntity(portEventsByYear));
    }

    @Override
    public Set<PortEventsByMonth> searchAll() {
        return all().stream().map(HibernatePortEventsByMonthEntity::toEntity).collect(Collectors.toSet());
    }

    @Override
    public Optional<PortEventsByMonth> search(PortId portId, Year year, Month month) {
        String query = "SELECT p FROM portEventsByMonth p WHERE p.portId = :portId AND p.year = :year AND p.month = :month";
        return sessionFactory.getCurrentSession()
            .createQuery(query, this.aggregateClass)
            .setParameter("portId", portId.value())
            .setParameter("year", year.value())
            .setParameter("month", month.value())
            .uniqueResultOptional()
            .map(HibernatePortEventsByMonthEntity::toEntity);
    }
}
