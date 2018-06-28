package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Discount;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = {"deleted", "company", "companyId"})
public interface DiscountMapper extends EntityDTOMapper<Discount, DiscountDTO> {
}
