package com.shiproutes.backoffice.user.infrastructure.persistence;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.backoffice.user.infrastructure.persistence.hibernate.HibernateUserEntity;
import com.shiproutes.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional("backoffice-transaction_manager")
public class MySqlUserRepository extends HibernateRepository<HibernateUserEntity> implements UserRepository {

    public MySqlUserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HibernateUserEntity.class);
    }

    @Override
    public void save(User user) {
        persist(new HibernateUserEntity(user));
    }

    @Override
    public Optional<User> search(Username username) {
        var sql = "select u from user u where u.username = :username";
        return sessionFactory.getCurrentSession().createQuery(sql, HibernateUserEntity.class)
            .setParameter("username", username.value())
            .uniqueResultOptional()
            .map(HibernateUserEntity::toEntity);
    }

    @Override
    public boolean exists(Username username, UserEmail email) {
        var sql = "select u from user u where u.username = :username or u.email = :email";
        return sessionFactory.getCurrentSession().createQuery(sql, HibernateUserEntity.class)
            .setParameter("username", username.value())
            .setParameter("email", email.value())
            .uniqueResultOptional()
            .isPresent();
    }

    @Override
    public Set<User> search(String partialUsername) {
        var sql = "select u from user u where u.username like :partialUsername";
        return sessionFactory.getCurrentSession().createQuery(sql, HibernateUserEntity.class)
            .setParameter("partialUsername", "%" + partialUsername + "%")
            .list().stream()
            .map(HibernateUserEntity::toEntity)
            .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> search(UserId userId) {
        return byId(userId.value()).map(HibernateUserEntity::toEntity);
    }

}
