package com.violetbutterfly.drinkoff.web.security;

import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.persistence.entity.User;

public class ResourceAccess {

    private static final String ACCESS_DENIED = "Access is denied";
    private static final String ACCESS_DENIED_ATTRIBUTES = "Access is denied. No Permission to change these attributes";

    public static void verify(User loggedUser, UserDTO userDTO) {
        if (loggedUser == null || userDTO == null) {
            throw new UserAuthenticationException(ACCESS_DENIED_ATTRIBUTES);
        }
        if (loggedUser.getType() != Role.ADMIN &&
                (loggedUser.getType() != userDTO.getType())) {
            throw new UserAuthenticationException(ACCESS_DENIED_ATTRIBUTES);
        }
        verifyUserId(loggedUser, userDTO.getId());
    }

    public static void verify(User loggedUser, User user) {
        verifyUserId(loggedUser, user == null ? null : user.getId());
    }

    public static void verify(User loggedUser, CompanyNoCrnDTO company) {
        verifyUserId(loggedUser, company == null ? null : company.getUserId());
    }

    public static void verifyUserId(User loggedUser, String id) {
        if (loggedUser == null ||
                (loggedUser.getType() != Role.ADMIN && !loggedUser.getId().equals(id))) {
            throw new UserAuthenticationException(ACCESS_DENIED);
        }
    }
}
