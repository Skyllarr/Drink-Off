package com.violetbutterfly.drinkoff.web.controllers;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
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
@RequestMapping(path = Uri.ROOT_URI_COMPANY, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    @Inject
    private CompanyFacade companyFacade;

    @RequestMapping(path = Uri.Part.SIGN_UP_COMPANY, method = RequestMethod.POST)
    public SignUpCompanyDTO signUpCompany(@Valid @RequestBody SignUpCompanyDTO company) {
        companyFacade.signUpCompany(company);
        return company;
    }

    @RequestMapping(path = Uri.Part.UPDATE, method = RequestMethod.POST)
    public CompanyDTO update(@AuthenticationPrincipal User loggedUser, @Valid @RequestBody CompanyDTO company) {
        ResourceAccess.verify(loggedUser, company.getUser());
        return companyFacade.update(company);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@AuthenticationPrincipal User loggedUser, @PathVariable("id") String id) {
        ResourceAccess.verifyUserId(loggedUser, companyFacade.findById(id).getUserId());
        companyFacade.delete(id);
    }

    @RequestMapping(path = Uri.Part.ME, method = RequestMethod.GET)
    public CompanyNoCrnDTO getMyDetails(@AuthenticationPrincipal UserDTO loggedUser) {
        return companyFacade.findById(loggedUser.getId());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CompanyNoCrnDTO findById(@PathVariable("id") String id) {
        return companyFacade.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CompanyNoCrnDTO> findAll() {
        return companyFacade.findAll();
    }

    @RequestMapping(path = Uri.Part.FIND, method = RequestMethod.GET)
    public CompanyNoCrnDTO findByName(@RequestParam("name") String name) {
        return companyFacade.findByName(name);
    }
}
