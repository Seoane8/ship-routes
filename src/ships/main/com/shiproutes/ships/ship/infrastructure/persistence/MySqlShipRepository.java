package com.shiproutes.ships.ship.infrastructure.persistence;

import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import com.shiproutes.ships.ship.domain.IMO;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipRepository;
import com.shiproutes.ships.ship.infrastructure.persistence.hibernate.HibernateShipEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ships-transaction_manager")
public class MySqlShipRepository extends HibernateRepository<HibernateShipEntity> implements ShipRepository {

    public MySqlShipRepository(@Qualifier("ships-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, HibernateShipEntity.class);
    }

    @Override
    public void save(Ship ship) {
        super.persist(new HibernateShipEntity(ship));
    }

    @Override
    public Optional<Ship> search(IMO imo) {
        return Optional
            .ofNullable(sessionFactory.getCurrentSession()
                .byId(HibernateShipEntity.class)
                .load(imo.value()))
            .map(HibernateShipEntity::toEntity);
    }
}
