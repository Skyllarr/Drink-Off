package com.violetbutterfly.drinkoff.api.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CompanyWithUserDTO extends CompanyInfoDTO{
    @NotBlank
    private String id;

    @NotNull
    private UserDTO user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
