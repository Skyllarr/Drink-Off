package com.violetbutterfly.drinkoff.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Discount extends AbstractStringIdEntity {

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
}
