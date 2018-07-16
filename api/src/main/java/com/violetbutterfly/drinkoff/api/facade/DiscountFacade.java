package com.violetbutterfly.drinkoff.api.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;

import java.util.List;

public interface DiscountFacade {

    void create(DiscountDTO discount);

    DiscountDTO update(DiscountDTO discount);

    void delete(String id);

    List<DiscountDTO> findAll();

    DiscountDTO findById(String id);

    List<DiscountDTO> findByCompany(CompanyNoCrnDTO companyWithUserDTO);

    List<DiscountDTO> findByProduct(String product);

    List<DiscountDTO> getDiscounts(UserDTO user);
}
