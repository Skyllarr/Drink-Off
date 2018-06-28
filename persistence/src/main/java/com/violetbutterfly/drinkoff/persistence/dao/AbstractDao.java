package com.violetbutterfly.drinkoff.persistence.dao;

import com.violetbutterfly.drinkoff.persistence.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<M extends Serializable, T extends AbstractEntity<M>> {

    @PersistenceContext
    EntityManager em;

    private Class<T> clazz;

    AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void create(T t) {
        em.persist(t);
        em.flush();
    }

    public T update(T t) {
        T managedEntity = em.merge(t);
        em.flush();
        return managedEntity;
    }

    public void delete(T t) {
        t.setDeleted(true);
        em.merge(t);
        em.flush();
    }

    public void delete(T t, boolean force) {
        if (force) {
            em.remove(t);
        } else {
            delete(t);
        }
    }

    public T findById(M id) {
        try {
            return em.createQuery("select c from " + clazz.getName() + " c where c.deleted = :deleted and c.id = :id", clazz)
                    .setParameter("deleted", false)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public T findById(M id, boolean deleted) {
        if (deleted) {
            return em.find(clazz, id);
        } else {
            return findById(id);
        }
    }

    public List<T> findAll() {
        return em.createQuery("select c from " + clazz.getName() + " c where c.deleted = :deleted", clazz)
                .setParameter("deleted", false)
                .getResultList();
    }

    public List<T> findAll(boolean deleted) {
        if (deleted) {
            return em.createQuery("select c from " + clazz.getName() + " c", clazz)
                    .getResultList();
        } else {
            return findAll();
        }
    }
}
