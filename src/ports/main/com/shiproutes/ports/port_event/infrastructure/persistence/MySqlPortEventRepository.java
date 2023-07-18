package com.shiproutes.ports.port_event.infrastructure.persistence;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.ports.port_event.domain.PortEventId;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
import com.shiproutes.ports.port_event.infrastructure.persistence.hibernate.HibernatePortEventEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlPortEventRepository extends HibernateRepository<HibernatePortEventEntity> implements PortEventRepository {

    public MySqlPortEventRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortEventEntity.class);
    }

    @Override
    public void save(PortEvent portEvent) {
        persist(new HibernatePortEventEntity(portEvent));
    }

    @Override
    public Optional<PortEvent> search(PortEventId id) {
        return byId(id.value()).map(HibernatePortEventEntity::toEntity);
    }

    @Override
    public Set<PortEvent> searchBetweenDates(PortEventDate startDate, PortEventDate endDate) {
        String sql = "SELECT p FROM portEvent p WHERE p.date BETWEEN :startDate AND :endDate";
        return sessionFactory.getCurrentSession().createQuery(sql, this.aggregateClass)
                .setParameter("startDate", startDate.value())
                .setParameter("endDate", endDate.value())
                .getResultList()
                .stream()
                .map(HibernatePortEventEntity::toEntity)
                .collect(java.util.stream.Collectors.toSet());
    }
}
