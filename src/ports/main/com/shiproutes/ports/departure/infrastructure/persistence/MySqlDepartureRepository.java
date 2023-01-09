package com.shiproutes.ports.departure.infrastructure.persistence;

import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureId;
import com.shiproutes.ports.departure.domain.DepartureRepository;
import com.shiproutes.ports.departure.infrastructure.persistence.hibernate.HibernateDepartureEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlDepartureRepository
    extends HibernateRepository<HibernateDepartureEntity> implements DepartureRepository {

    public MySqlDepartureRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateDepartureEntity.class);
    }

    @Override
    public void save(Departure departure) {
        persist(new HibernateDepartureEntity(departure));
    }

    @Override
    public Optional<Departure> search(DepartureId id) {
        return byId(id.value()).map(HibernateDepartureEntity::toEntity);
    }

}
