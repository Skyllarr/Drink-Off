package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

@Service
public class UserMapperService extends EntityDTOServiceImpl<User, UserDTO> {

    private UserMapper mapper = Selma.builder(UserMapper.class).build();

    @SuppressWarnings("unchecked")
    @Override
    public UserMapper getMapper() {
        return mapper;
    }
}
