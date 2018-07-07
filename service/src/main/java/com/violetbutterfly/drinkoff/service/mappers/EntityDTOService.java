package com.violetbutterfly.drinkoff.service.mappers;

import java.util.Collection;
import java.util.List;

public interface EntityDTOService<Entity, DTO> extends EntityDTOMapper<Entity, DTO> {

    <Mapper extends EntityDTOMapper<Entity, DTO>> Mapper getMapper();

    DTO asDTO(Entity entity);

    Entity asEntity(DTO dto);

    List<DTO> asDtos(Collection<Entity> entities);

    List<Entity> asEntities(Collection<DTO> dtos);
}
