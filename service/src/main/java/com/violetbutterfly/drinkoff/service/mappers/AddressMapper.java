package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.AddressDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = {"deleted"})
public interface AddressMapper extends EntityDTOMapper<Address, AddressDTO> {
}
