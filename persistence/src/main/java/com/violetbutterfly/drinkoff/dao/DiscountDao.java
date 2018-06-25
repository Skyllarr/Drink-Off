package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.Company;
import com.violetbutterfly.drinkoff.entity.Discount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DiscountDao extends AbstractStringIdEntityDao<Discount> {
    @PersistenceContext
    private EntityManager em;

    public DiscountDao() {
        super(Discount.class);
    }

    public List<Discount> findByProduct(String product) {
        return em.createQuery("select d from Discount d WHERE lower(product) like CONCAT('%', lower(:product), '%') and d.deleted = :deleted",
                Discount.class)
                .setParameter("deleted", false)
                .setParameter("product", product)
                .getResultList();
    }

    public List<Discount> findByCompany(Company company) {
        return em.createQuery("select c from Discount c WHERE c.company.id = :companyId and c.deleted = :deleted",
                Discount.class)
                .setParameter("deleted", false)
                .setParameter("companyId", company.getId())
                .getResultList();
    }
}
