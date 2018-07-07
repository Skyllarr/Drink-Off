package com.violetbutterfly.drinkoff.service.mappers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract class EntityDTOServiceImpl<Entity, DTO> implements EntityDTOService<Entity, DTO> {

    public abstract <Mapper extends EntityDTOMapper<Entity, DTO>> Mapper getMapper();

    public DTO asDTO(Entity entity) {
        return entity == null ? null : getMapper().asDTO(entity);
    }

    public Entity asEntity(DTO dto) {
        return dto == null ? null : getMapper().asEntity(dto);
    }

    public List<DTO> asDtos(Collection<Entity> entities) {
        return entities == null ? Collections.emptyList() : entities.stream().map(this::asDTO).collect(Collectors.toList());
    }

    public List<Entity> asEntities(Collection<DTO> dtos) {
        return dtos == null ? Collections.emptyList() : dtos.stream().map(this::asEntity).collect(Collectors.toList());
    }
}
