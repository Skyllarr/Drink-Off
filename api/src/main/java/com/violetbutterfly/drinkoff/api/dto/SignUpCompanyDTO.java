package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpCompanyDTO extends CompanyInfoDTO {
    @NotNull
    private SignUpAddressDTO address;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    @NotBlank
    @Size(max = 200)
    private String crn; // CRN (Company Registration Number) == ICO in Czech Republic

    public SignUpAddressDTO getAddress() {
        return address;
    }

    public void setAddress(SignUpAddressDTO address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }
}
