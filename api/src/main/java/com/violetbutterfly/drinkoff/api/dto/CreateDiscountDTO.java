package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.NotBlank;

public class CreateDiscountDTO {
    @NotBlank
    private String companyId;

    @NotBlank
    private String product;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
