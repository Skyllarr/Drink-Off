package com.violetbutterfly.drinkoff.api.dto;

import com.violetbutterfly.drinkoff.api.enums.Role;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull
    private String id;

    @NotNull
    @Size(min = 4, max = 20)
    private String nick;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Role type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }
}
