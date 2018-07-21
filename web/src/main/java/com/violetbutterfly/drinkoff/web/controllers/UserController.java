package com.violetbutterfly.drinkoff.web.controllers;

import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.UserFacade;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.web.Uri;
import com.violetbutterfly.drinkoff.web.security.ResourceAccess;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = Uri.ROOT_URI_USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Inject
    private UserFacade userFacade;

    @RequestMapping(path = Uri.Part.UPDATE, method = RequestMethod.POST)
    public UserDTO update(@AuthenticationPrincipal User loggedUser, @Valid @RequestBody UserDTO user) {
        ResourceAccess.verify(loggedUser, user);
        return userFacade.update(user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@AuthenticationPrincipal User loggedUser, @PathVariable("id") String id) {
        ResourceAccess.verifyUserId(loggedUser, id);
        userFacade.delete(id);
    }

    @RequestMapping(path = Uri.Part.ME, method = RequestMethod.GET)
    public UserDTO getMyDetails(@AuthenticationPrincipal UserDTO loggedUser) {
        return userFacade.findById(loggedUser.getId());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserDTO findById(@AuthenticationPrincipal User loggedUser, @PathVariable("id") String id) {
        ResourceAccess.verifyUserId(loggedUser, id);
        return userFacade.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> findAll() {
        return userFacade.findAll();
    }

    @RequestMapping(path = Uri.Part.FIND, method = RequestMethod.GET)
    public UserDTO findByEmail(@AuthenticationPrincipal User loggedUser, @RequestParam("email") String email) {
        UserDTO user = userFacade.findByEmail(email);
        ResourceAccess.verify(loggedUser, user);
        return user;
    }
}
