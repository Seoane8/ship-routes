package com.shiproutes.ingest.port.infrastructure.persistence;

import com.shiproutes.ingest.port.domain.IngestPort;
import com.shiproutes.ingest.port.domain.IngestPortRepository;
import com.shiproutes.ingest.port.infrastructure.persistence.hibernate.HibernateIngestPortEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ingest-transaction_manager")
public class MySqlIngestPortRepository
    extends HibernateRepository<HibernateIngestPortEntity>
    implements IngestPortRepository {

    public MySqlIngestPortRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateIngestPortEntity.class);
    }

    @Override
    public void save(IngestPort port) {
        persist(new HibernateIngestPortEntity(port));
    }

    @Override
    public Optional<IngestPort> searchByLocode(String locode) {
        var sql = "SELECT p FROM ingestPort p WHERE p.locode = :locode";
        return sessionFactory.getCurrentSession().createQuery(sql, HibernateIngestPortEntity.class)
            .setParameter("locode", locode)
            .uniqueResultOptional()
            .map(HibernateIngestPortEntity::toEntity);
    }
}
