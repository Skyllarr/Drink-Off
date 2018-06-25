package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao extends AbstractStringIdEntityDao<User> {
    @PersistenceContext
    private EntityManager em;

    public UserDao() {
        super(User.class);
    }

    public List<User> findByNick(String nick) {
        return em.createQuery("select u from User u WHERE lower(nick) like CONCAT('%', lower(:nick), '%') and u.deleted = :deleted", User.class)
                .setParameter("deleted", false)
                .setParameter("nick", nick)
                .getResultList();
    }

    public User findByEmail(String email) {
        try {
            return em.createQuery("select u from User u WHERE lower(email) like CONCAT('%', lower(:email), '%') and u.deleted = :deleted", User.class)
                    .setParameter("deleted", false)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
