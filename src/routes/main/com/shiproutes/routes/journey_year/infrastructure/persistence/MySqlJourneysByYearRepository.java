package com.shiproutes.routes.journey_year.infrastructure.persistence;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearRepository;
import com.shiproutes.routes.journey_year.infrastructure.persistence.hibernate.HibernateJourneysByYearEntity;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("routes-transaction_manager")
public class MySqlJourneysByYearRepository
    extends HibernateRepository<HibernateJourneysByYearEntity>
    implements JourneysByYearRepository {

    public MySqlJourneysByYearRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateJourneysByYearEntity.class);
    }

    @Override
    public void save(JourneysByYear journeysByYear) {
        persist(new HibernateJourneysByYearEntity(journeysByYear));
    }

    @Override
    public Optional<JourneysByYear> search(PortId originPort, PortId destinationPort, Year year) {
        var sql = "SELECT j FROM journeysByYear j " +
            "WHERE j.originPort = :originPort AND j.destinationPort = :destinationPort AND j.year = :year";
        return sessionFactory.getCurrentSession().createQuery(sql, this.aggregateClass)
            .setParameter("originPort", originPort.value())
            .setParameter("destinationPort", destinationPort.value())
            .setParameter("year", year.value())
            .uniqueResultOptional()
            .map(HibernateJourneysByYearEntity::toEntity);
    }

    @Override
    public Set<JourneysByYear> searchAll() {
        return all().stream().map(HibernateJourneysByYearEntity::toEntity).collect(Collectors.toSet());
    }
}
