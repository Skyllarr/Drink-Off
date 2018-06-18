package com.violetbutterfly.drinkoff.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Company extends AbstractEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(address, company.address) &&
                Objects.equals(user, company.user) &&
                Objects.equals(phoneNumber, company.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, user, phoneNumber);
    }
}
