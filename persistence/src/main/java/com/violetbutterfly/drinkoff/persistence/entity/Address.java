package com.violetbutterfly.drinkoff.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Address extends AbstractStringIdEntity {

    @Column(nullable = false, length = 250)
    private String street;

    @Column(nullable = false, length = 50)
    private String houseNumber;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 20)
    private String zipcode;

    public Address() {
    }

    public Address(String street, String houseNumber, String city, String state, String country, String zipcode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String state) {
        this.country = state;
    }

    public String getStreet() {
        return this.street;
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
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String county) {
        this.state = county;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
