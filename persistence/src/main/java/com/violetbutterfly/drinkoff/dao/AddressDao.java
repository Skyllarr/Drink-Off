package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AddressDao extends AbstractDao<Address> {

    public AddressDao() {
        super(Address.class);
    }

    public List<Address> findByCountry(String country) {
        return getCountries(country)
                .getResultList();
    }

    public List<Address> findByCountry(String country, int maxResults) {
        return getCountries(country)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public List<Address> findByCity(String city) {
        return getCities(city)
                .getResultList();
    }

    public List<Address> findByCity(String city, int maxResults) {
        return getCities(city)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public List<Address> findByState(String state) {
        return getStates(state)
                .getResultList();
    }

    public List<Address> findByState(String state, int maxResults) {
        return getStates(state)
                .setMaxResults(maxResults)
                .getResultList();
    }

    private TypedQuery<Address> getCountries(String country) {
        return em.createQuery("SELECT h FROM Address h WHERE lower(country) like CONCAT('%', lower(:country), '%')",
                Address.class)
                .setParameter("country", country.toLowerCase());
    }

    private TypedQuery<Address> getStates(String state) {
        return em.createQuery("SELECT h FROM Address h WHERE lower(state) like CONCAT('%', lower(:state), '%')",
                Address.class)
                .setParameter("state", state.toLowerCase());
    }

    private TypedQuery<Address> getCities(String city) {
        return em.createQuery("SELECT h FROM Address h WHERE lower(city) like CONCAT('%', lower(:city), '%')",
                Address.class)
                .setParameter("city", city.toLowerCase());
    }
}
