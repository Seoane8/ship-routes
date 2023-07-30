package com.shiproutes.ingest.ship.infrastructure.persistence;

import com.shiproutes.ingest.ship.domain.IngestShip;
import com.shiproutes.ingest.ship.domain.IngestShipRepository;
import com.shiproutes.ingest.ship.infrastructure.persistence.hibernate.HibernateIngestShipEntity;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ingest-transaction_manager")
public class MySqlIngestShipRepository
    extends HibernateRepository<HibernateIngestShipEntity>
    implements IngestShipRepository {

    public MySqlIngestShipRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateIngestShipEntity.class);
    }

    @Override
    public void save(IngestShip ship) {
        persist(new HibernateIngestShipEntity(ship));
    }

    @Override
    public Optional<IngestShip> search(IMO imo) {
        return byId(imo.value()).map(HibernateIngestShipEntity::toEntity);
    }
}
