package com.shiproutes.routes.journey.infrastructure.persistence;

import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyId;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import com.shiproutes.routes.journey.infrastructure.persistence.hibernate.HibernateJourneyEntity;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
            "j.arrivalDate > :departureDate AND (j.departureDate < :departureDate OR j.departureDate IS NULL)";
        return sessionFactory.getCurrentSession()
            .createQuery(sql, HibernateJourneyEntity.class)
            .setParameter("shipId", shipId.value())
            .setParameter("departureDate", departureDate.value())
            .uniqueResultOptional()
            .map(HibernateJourneyEntity::toEntity);
    }

    @Override
    public void remove(Journey journey) {
        remove(new HibernateJourneyEntity(journey));
    }
}
