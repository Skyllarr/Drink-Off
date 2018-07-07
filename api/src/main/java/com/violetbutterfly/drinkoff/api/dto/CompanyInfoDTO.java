package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyInfoDTO {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 2000)
    private String url;

    @NotBlank
    @Size(max = 200)
    private String crn; // CRN (Company Registration Number) == ICO in Czech Republic

    @Size(max = 2500)
    private String description;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotNull
    private AddressDTO address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }
}
