package com.violetbutterfly.drinkoff.api.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;

import java.util.List;

public interface DiscountFacade {

    void create(DiscountDTO discount);

    DiscountDTO update(DiscountDTO discount);

    void delete(String id);

    List<DiscountDTO> findAll();

    DiscountDTO findById(String id);

    List<DiscountDTO> findByCompany(CompanyDTO companyWithUserDTO);

    List<DiscountDTO> findByProduct(String product);
}
