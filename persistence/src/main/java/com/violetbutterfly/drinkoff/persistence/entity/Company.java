package com.violetbutterfly.drinkoff.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Company extends AbstractStringIdEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 2083)
    private String url;

    @Column(nullable = false, unique = true, length = 200)
    private String ico;

    @Column(nullable = true, length = 2500)
    private String description;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(address, company.address) &&
                Objects.equals(user, company.user) &&
                Objects.equals(ico, company.ico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, user, ico);
    }
}
