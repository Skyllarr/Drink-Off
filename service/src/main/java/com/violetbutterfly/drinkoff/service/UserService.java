package com.violetbutterfly.drinkoff.service;

import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.persistence.entity.User;

public interface UserService {

    boolean authenticate(User user, String password) throws UserAuthenticationException;

    User changePassword(User user, String oldPassword, String newPassword) throws UserAuthenticationException;

    User findByEmail(String email);
}
