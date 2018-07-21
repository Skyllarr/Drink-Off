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
    public void changePassword(User user, String oldUnencryptedPassword, String newUnencryptedPassword)
            throws UserAuthenticationException {
        if (StringUtils.isEmpty(oldUnencryptedPassword)) {
            throw new IllegalArgumentException("old Password is empty");
        }
        if (StringUtils.isEmpty(newUnencryptedPassword)) {
            throw new IllegalArgumentException("new Password is empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User id null");
        }
        if (AuthenticationUtils.verifyPassword(oldUnencryptedPassword, user.getPasswordHash())) {
            user.setPasswordHash(AuthenticationUtils.createHash(newUnencryptedPassword));
            userDao.update(user);
        }
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
