package com.shiproutes.routes.journey.infrastructure.persistence;

import com.shiproutes.routes.journey.domain.*;
import com.shiproutes.routes.journey.infrastructure.persistence.hibernate.HibernateJourneyEntity;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("routes-transaction_manager")
public class MySqlJourneyRepository extends HibernateRepository<HibernateJourneyEntity> implements JourneyRepository {


    public MySqlJourneyRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateJourneyEntity.class);
    }

    @Override
    public void save(Journey journey) {
        persist(new HibernateJourneyEntity(journey));
    }

    @Override
    public Optional<Journey> search(JourneyId id) {
        return byId(id.value()).map(HibernateJourneyEntity::toEntity);
    }

    @Override
    public Optional<Journey> searchJourneyArrival(IMO shipId, DepartureDate departureDate) {
        var sql = "SELECT j FROM journey j WHERE j.shipId = :shipId AND " +
            "j.arrivalDate > :departureDate AND (j.departureDate < :departureDate OR j.departureDate IS NULL)" +
            "order by j.arrivalDate asc";
        return sessionFactory.getCurrentSession()
            .createQuery(sql, HibernateJourneyEntity.class)
            .setParameter("shipId", shipId.value())
            .setParameter("departureDate", departureDate.value())
            .setFirstResult(0).setMaxResults(1)
            .uniqueResultOptional()
            .map(HibernateJourneyEntity::toEntity);
    }

    @Override
    public Optional<Journey> searchJourneyDeparture(IMO shipId, ArrivalDate arrivalDate) {
        var sql = "SELECT j FROM journey j WHERE j.shipId = :shipId AND " +
            "j.departureDate < :arrivalDate AND (j.arrivalDate > :arrivalDate OR j.arrivalDate IS NULL)" +
            "order by j.departureDate desc";
        return sessionFactory.getCurrentSession()
            .createQuery(sql, HibernateJourneyEntity.class)
            .setParameter("shipId", shipId.value())
            .setParameter("arrivalDate", arrivalDate.value())
            .setFirstResult(0).setMaxResults(1)
            .uniqueResultOptional()
            .map(HibernateJourneyEntity::toEntity);
    }

    @Override
    public void remove(Journey journey) {
        var sql = "DELETE FROM journey j WHERE j.id = :id";
        sessionFactory.getCurrentSession()
            .createQuery(sql)
            .setParameter("id", journey.id().value())
            .executeUpdate();
    }

    @Override
    public Set<Journey> searchBetweenDates(DepartureDate startDate, DepartureDate endDate) {
        var sql = "SELECT j FROM journey j WHERE j.departureDate BETWEEN :startDate AND :endDate";
        return sessionFactory.getCurrentSession()
            .createQuery(sql, HibernateJourneyEntity.class)
            .setParameter("startDate", startDate.value())
            .setParameter("endDate", endDate.value())
            .getResultList()
            .stream()
            .map(HibernateJourneyEntity::toEntity)
            .collect(Collectors.toSet());
    }
}
