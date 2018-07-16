package com.violetbutterfly.drinkoff.service.facade;

import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.api.facade.UserFacade;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.service.UserService;
import com.violetbutterfly.drinkoff.service.mappers.UserMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserDao userDao;

    @Inject
    private UserService userService;

    @Inject
    private UserMapperService userMapperService;

    @Override
    public UserDTO update(UserDTO user) {
        return userMapperService.asDTO(userDao.update(userMapperService.asEntity(user)));
    }

    @Override
    public void delete(UserDTO userDTO) {
        userDao.delete(userMapperService.asEntity(userDTO));
    }

    @Override
    public void delete(String userId) {
        userDao.delete(userDao.findById(userId));
    }

    @Override
    public UserDTO findById(String id) {
        return userMapperService.asDTO(userDao.findById(id));
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapperService.asDTO(userDao.findByEmail(email));
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapperService.asDtos(userDao.findAll());
    }

    @Override
    public boolean authenticate(UserDTO userDTO, String password) throws UserAuthenticationException {
        return userService.authenticate(userMapperService.asEntity(userDTO), password);
    }

    @Override
    public void changePassword(UserDTO userDTO, String oldUnencryptedPassword, String newUnencryptedPassword) throws UserAuthenticationException {
        userService.changePassword(userMapperService.asEntity(userDTO), oldUnencryptedPassword, newUnencryptedPassword);
    }
}
