package com.violetbutterfly.drinkoff.entity;

import com.violetbutterfly.drinkoff.enums.Role;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Account")
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String nick;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role type;

    public User() {
    }

    public User(String nick, String email, String passwordHash, Role type) {
        this.nick = nick;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}