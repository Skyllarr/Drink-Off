package com.violetbutterfly.drinkoff.service;

import com.violetbutterfly.drinkoff.api.dto.*;
import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.Discount;
import com.violetbutterfly.drinkoff.persistence.entity.User;

public class ObjectsHelper {

    public static UserDTO getUserDTO() {
        User user = getUserEntity();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setNick(user.getNick());
        userDTO.setType(Role.COMPANY);
        return userDTO;
    }

    public static User getUserEntity() {
        User user = new User();
        user.setId("user_id");
        user.setNick("my_nick");
        user.setEmail("email@email.com");
        user.setType(Role.COMPANY);
        user.setPasswordHash(AuthenticationUtils.createHash("password"));
        return user;
    }

    public static Company getCompanyEntity() {
        Company company = new Company();
        company.setUrl("www.url.org");
        company.setCrn("company_ico");
        company.setId("company_id");
        company.setName("company_name");
        company.setDescription("This is company description.");
        company.setPhoneNumber("+420 123 456 789");
        company.setUser(getUserEntity());
        company.setAddress(getAddressEntity());
        return company;
    }

    public static Address getAddressEntity() {
        Address address = new Address();
        address.setId("address_id");
        address.setCity("Dublin");
        address.setStreet("Dublin Street");
        address.setHouseNumber("46");
        address.setCountry("Ireland");
        address.setZipcode("48521");
        address.setState("county");
        return address;
    }

    public static CompanyNoCrnDTO getCompanyNoCrnDTO() {
        Company company = getCompanyEntity();
        CompanyNoCrnDTO companyNoCrnDTO = new CompanyNoCrnDTO();
        companyNoCrnDTO.setName(company.getName());
        companyNoCrnDTO.setPhoneNumber(company.getPhoneNumber());
        companyNoCrnDTO.setUserId(getUserDTO().getId());
        companyNoCrnDTO.setUrl(company.getUrl());
        companyNoCrnDTO.setId(company.getId());
        companyNoCrnDTO.setAddress(getAddressDTO());
        companyNoCrnDTO.setDescription(company.getDescription());
        return companyNoCrnDTO;
    }

    public static CompanyDTO getCompanyDTO() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCrn(getCompanyEntity().getCrn());
        return companyDTO;
    }

    public static SignUpCompanyDTO getSignUpCompanyDTO() {
        Company company = getCompanyEntity();
        User user = getUserEntity();
        SignUpCompanyDTO signUpCompanyDTO = new SignUpCompanyDTO();
        signUpCompanyDTO.setEmail(user.getEmail());
        signUpCompanyDTO.setPassword("password");
        signUpCompanyDTO.setCrn(company.getCrn());
        signUpCompanyDTO.setName(company.getName());
        signUpCompanyDTO.setUrl(company.getUrl());
        signUpCompanyDTO.setPhoneNumber(company.getPhoneNumber());
        signUpCompanyDTO.setAddress(getSignUpAddressDTO());
        return signUpCompanyDTO;
    }

    public static AddressDTO getAddressDTO() {
        Address address = getAddressEntity();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHouseNumber(address.getHouseNumber());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setState(address.getState());
        return addressDTO;
    }

    public static SignUpAddressDTO getSignUpAddressDTO() {
        Address address = getAddressEntity();
        SignUpAddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHouseNumber(address.getHouseNumber());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setState(address.getState());
        return addressDTO;
    }

    public static DiscountDTO getDiscountDTO() {
        Discount discount = new Discount();
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setCompanyId(getCompanyDTO().getId());
        discountDTO.setProduct(discount.getProduct());
        return discountDTO;
    }

    public static Discount getDiscount() {
        Discount discount = new Discount();
        discount.setCompany(getCompanyEntity());
        discount.setProduct("beer");
        return discount;
    }

    public static CompanyDTO getCompanyWithUserDTO() {
        Company company = getCompanyEntity();
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setPhoneNumber(company.getPhoneNumber());
        companyDTO.setUser(getUserDTO());
        companyDTO.setCrn(company.getCrn());
        companyDTO.setUrl(company.getUrl());
        companyDTO.setId(company.getId());
        companyDTO.setAddress(getAddressDTO());
        companyDTO.setDescription(company.getDescription());
        return companyDTO;
    }
}
