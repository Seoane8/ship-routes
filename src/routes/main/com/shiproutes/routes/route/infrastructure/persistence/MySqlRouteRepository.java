package com.shiproutes.routes.route.infrastructure.persistence;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteId;
import com.shiproutes.routes.route.domain.RouteRepository;
import com.shiproutes.routes.route.infrastructure.persistence.hibernate.HibernateRouteEntity;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("routes-transaction_manager")
public class MySqlRouteRepository extends HibernateRepository<HibernateRouteEntity> implements RouteRepository {


    public MySqlRouteRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateRouteEntity.class);
    }

    @Override
    public void save(Route route) {
        persist(new HibernateRouteEntity(route));
    }

    @Override
    public Optional<Route> search(RouteId id) {
        return byId(id.value()).map(HibernateRouteEntity::toEntity);
    }

    @Override
    public Optional<Route> search(PortId originPort, PortId destinationPort) {
        var sql = "SELECT r FROM route r WHERE r.departurePort = :origin_port AND r.arrivalPort = :destination_port";
        return sessionFactory.getCurrentSession().createQuery(sql, this.aggregateClass)
            .setParameter("origin_port", originPort.value())
            .setParameter("destination_port", destinationPort.value())
            .uniqueResultOptional()
            .map(HibernateRouteEntity::toEntity);
    }
}
