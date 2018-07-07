package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DiscountDTO {
    @NotBlank
    private String id;

    @NotBlank
    private String companyId;

    @NotBlank
    private String product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String company) {
        this.companyId = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
