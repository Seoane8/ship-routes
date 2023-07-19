package com.shiproutes.routes.port.infrastructure.persistence;

import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.routes.port.domain.RoutePortRepository;
import com.shiproutes.routes.port.infrastructure.persistence.hibernate.HibernateRoutePortEntity;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("ports-transaction_manager")
public class MySqlRoutePortRepository
    extends HibernateRepository<HibernateRoutePortEntity>
    implements RoutePortRepository {

    public MySqlRoutePortRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateRoutePortEntity.class);
    }

    @Override
    public void save(RoutePort port) {
        persist(new HibernateRoutePortEntity(port));
    }

    @Override
    public Optional<RoutePort> search(PortId imo) {
        return byId(imo.value()).map(HibernateRoutePortEntity::toEntity);
    }
}
