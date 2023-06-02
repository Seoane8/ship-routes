package com.shiproutes.ports.ship.infrastructure.persistence;

import com.shiproutes.ports.ship.domain.PortShip;
import com.shiproutes.ports.ship.domain.PortShipRepository;
import com.shiproutes.ports.ship.infrastructure.persistence.hibernate.HibernatePortShipEntity;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlPortShipRepository
    extends HibernateRepository<HibernatePortShipEntity>
    implements PortShipRepository {

    public MySqlPortShipRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortShipEntity.class);
    }

    @Override
    public void save(PortShip ship) {
        persist(new HibernatePortShipEntity(ship));
    }

    @Override
    public Optional<PortShip> search(IMO imo) {
        return byId(imo.value()).map(HibernatePortShipEntity::toEntity);
    }
}
