package com.shiproutes.routes.journey_month.infrastructure.persistence;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthRepository;
import com.shiproutes.routes.journey_month.infrastructure.persistence.hibernate.HibernateJourneysByMonthEntity;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("routes-transaction_manager")
public class MySqlJourneysByMonthRepository
    extends HibernateRepository<HibernateJourneysByMonthEntity>
    implements JourneysByMonthRepository {

    public MySqlJourneysByMonthRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateJourneysByMonthEntity.class);
    }

    @Override
    public void save(JourneysByMonth journeysByMonth) {
        persist(new HibernateJourneysByMonthEntity(journeysByMonth));
    }

    @Override
    public Optional<JourneysByMonth> search(PortId originPort, PortId destinationPort, Month month, Year year) {
        var sql = "SELECT j FROM journeysByMonth j " +
            "WHERE j.originPort = :originPort AND j.destinationPort = :destinationPort " +
            "AND j.month = :month AND j.year = :year";
        return sessionFactory.getCurrentSession().createQuery(sql, this.aggregateClass)
            .setParameter("originPort", originPort.value())
            .setParameter("destinationPort", destinationPort.value())
            .setParameter("month", month.value())
            .setParameter("year", year.value())
            .uniqueResultOptional()
            .map(HibernateJourneysByMonthEntity::toEntity);
    }
}
