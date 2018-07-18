package com.violetbutterfly.drinkoff.service;

import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public boolean authenticate(User user, String password) throws UserAuthenticationException {
        return user != null && !StringUtils.isEmpty(password) && AuthenticationUtils.verifyPassword(password, user.getPasswordHash());
    }

    @Override
    public User changePassword(User user, String oldUnencryptedPassword, String newUnencryptedPassword)
            throws UserAuthenticationException {
        if (AuthenticationUtils.verifyPassword(oldUnencryptedPassword, user.getPasswordHash())) {
            user.setPasswordHash(AuthenticationUtils.createHash(newUnencryptedPassword));
            return userDao.update(user);
        } else {
            throw new IllegalArgumentException("Invalid Old Password");
        }
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
