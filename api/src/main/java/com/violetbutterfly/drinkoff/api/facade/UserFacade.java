package com.violetbutterfly.drinkoff.api.facade;

import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;

import java.util.List;

public interface UserFacade {

    UserDTO update(UserDTO user);

    void delete(UserDTO user);

    void delete(String userId);

    UserDTO findById(String id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

    boolean authenticate(UserDTO userDTO, String password) throws UserAuthenticationException;

    UserDTO changePassword(String userId, String oldUnencryptedPassword, String newUnencryptedPassword) throws UserAuthenticationException;
}
