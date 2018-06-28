package com.violetbutterfly.drinkoff.persistence.dao;

import com.violetbutterfly.drinkoff.persistence.entity.Address;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Component
public class CompanyDao extends AbstractStringIdEntityDao<Company> {

    @PersistenceContext
    private EntityManager em;

    public CompanyDao() {
        super(Company.class);
    }

    public Company findByName(String name) {
        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.deleted = :deleted and lower(name) like CONCAT('%', lower(:name), '%')",
                    Company.class)
                    .setParameter("deleted", false)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Company findByPhoneNumber(String phoneNumber) {
        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.deleted = :deleted and lower(phoneNumber) like CONCAT('%', lower(:phoneNumber), '%')",
                    Company.class)
                    .setParameter("deleted", false)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Company findByAddressId(Address address) {
        try {
            return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.address.id = :addressId",
                    Company.class)
                    .setParameter("deleted", false)
                    .setParameter("addressId", address.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Company findByUser(User user) {
        try {
            return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.user.id = :userId",
                    Company.class)
                    .setParameter("deleted", false)
                    .setParameter("userId", user.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Company> findByState(String state) {
        return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.address.state = :state",
                Company.class)
                .setParameter("deleted", false)
                .setParameter("state", state)
                .getResultList();
    }

    public List<Company> findByCity(String city) {
        return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.address.city = :city",
                Company.class)
                .setParameter("deleted", false)
                .setParameter("city", city)
                .getResultList();
    }

    public List<Company> findByCountry(String country) {
        return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.address.country = :country",
                Company.class)
                .setParameter("deleted", false)
                .setParameter("country", country)
                .getResultList();
    }

    public List<Company> findByUrl(String url) {
        return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.url = :url",
                Company.class)
                .setParameter("deleted", false)
                .setParameter("url", url)
                .getResultList();
    }

    public Company findByIco(String ico) {
        try {
            return em.createQuery("select c from Company c WHERE c.deleted = :deleted and c.crn = :ico",
                    Company.class)
                    .setParameter("deleted", false)
                    .setParameter("ico", ico)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
