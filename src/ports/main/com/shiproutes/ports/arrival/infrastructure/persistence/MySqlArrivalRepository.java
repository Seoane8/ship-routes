package com.shiproutes.ports.arrival.infrastructure.persistence;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import com.shiproutes.ports.arrival.infrastructure.persistence.hibernate.HibernateArrivalEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlArrivalRepository extends HibernateRepository<HibernateArrivalEntity> implements ArrivalRepository {

    public MySqlArrivalRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateArrivalEntity.class);
    }

    @Override
    public void save(Arrival arrival) {
        persist(new HibernateArrivalEntity(arrival));
    }

    @Override
    public Optional<Arrival> search(ArrivalId id) {
        return byId(id.value()).map(HibernateArrivalEntity::toEntity);
    }
}
