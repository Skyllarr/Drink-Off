package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = {"deleted", "user", "userId", "address"})
public interface CompanyMapper extends EntityDTOMapper<Company, CompanyDTO>{
}
