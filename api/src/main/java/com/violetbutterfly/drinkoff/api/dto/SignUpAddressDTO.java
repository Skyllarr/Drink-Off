package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class SignUpAddressDTO {
    @NotBlank
    @Size(max = 250)
    private String street;

    @NotBlank
    @Size(max = 50)
    private String houseNumber;

    @NotBlank
    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @NotBlank
    @Size(max = 100)
    private String country;

    @NotBlank
    @Size(max = 20)
    private String zipcode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
