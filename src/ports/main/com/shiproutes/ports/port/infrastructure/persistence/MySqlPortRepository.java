package com.shiproutes.ports.port.infrastructure.persistence;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.ports.port.infrastructure.persistence.hibernate.HibernatePortEntity;
import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlPortRepository extends HibernateRepository<HibernatePortEntity> implements PortRepository {

    public MySqlPortRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernatePortEntity.class);
    }

    @Override
    public void save(Port port) {
        persist(new HibernatePortEntity(port));
    }

    @Override
    public Optional<Port> search(PortId id) {
        return byId(id.value()).map(HibernatePortEntity::toEntity);
    }
}
