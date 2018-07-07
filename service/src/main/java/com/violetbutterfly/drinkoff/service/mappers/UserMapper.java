package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = {"deleted","passwordHash"})
public interface UserMapper extends EntityDTOMapper<User, UserDTO> {
}
