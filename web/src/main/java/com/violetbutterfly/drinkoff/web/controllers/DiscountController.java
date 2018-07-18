package com.violetbutterfly.drinkoff.web.controllers;

import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.api.facade.DiscountFacade;
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
@RequestMapping(path = Uri.ROOT_URI_DISCOUNT, produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountController {

    @Inject
    private DiscountFacade discountFacade;

    @Inject
    private CompanyFacade companyFacade;

    @Inject
    private UserFacade userFacade;

    @RequestMapping(path = Uri.Part.CREATE, method = RequestMethod.POST)
    public DiscountDTO create(@AuthenticationPrincipal User loggedUser, @Valid @RequestBody DiscountDTO discount) {
        ResourceAccess.verify(loggedUser, companyFacade.findById(discount.getCompanyId()));
        discountFacade.create(discount);
        return discount;
    }

    @RequestMapping(path = Uri.Part.UPDATE, method = RequestMethod.POST)
    public DiscountDTO update(@AuthenticationPrincipal User loggedUser, @Valid @RequestBody DiscountDTO discount) {
        ResourceAccess.verify(loggedUser, companyFacade.findById(discount.getCompanyId()));
        return discountFacade.update(discount);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@AuthenticationPrincipal User loggedUser, @PathVariable("id") String id) {
        ResourceAccess.verify(loggedUser, companyFacade.findById(discountFacade.findById(id).getCompanyId()));
        discountFacade.delete(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DiscountDTO findById(@PathVariable("id") String id) {
        return discountFacade.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DiscountDTO> findAll() {
        return discountFacade.findAll();
    }

    @RequestMapping(path = Uri.Part.FIND, method = RequestMethod.GET)
    public List<DiscountDTO> findByProduct(@RequestParam("product") String product) {
        return discountFacade.findByProduct(product);
    }

    @RequestMapping(path = "/mine", method = RequestMethod.GET)
    public List<DiscountDTO> getMyDiscounts(@AuthenticationPrincipal User loggedUser) {
        return discountFacade.getDiscounts(userFacade.findById(loggedUser.getId()));
    }
}
