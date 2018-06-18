package com.violetbutterfly.drinkoff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Discount extends AbstractEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Company company;

    @Column(nullable = false)
    private String product;

    public Discount() {
    }

    public Discount(Company company, String product) {
        this.company = company;
        this.product = product;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        if (!super.equals(o)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(company, discount.company) &&
                Objects.equals(product, discount.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, product);
    }
}
