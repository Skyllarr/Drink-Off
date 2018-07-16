package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.AddressDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

@Service
public class AddressMapperService extends EntityDTOServiceImpl<Address, AddressDTO> {

    private AddressMapper mapper = Selma.builder(AddressMapper.class).build();

    @SuppressWarnings("unchecked")
    @Override
    public AddressMapper getMapper() {
        return mapper;
    }
}
