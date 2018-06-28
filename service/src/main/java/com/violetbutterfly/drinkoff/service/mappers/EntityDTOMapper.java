package com.violetbutterfly.drinkoff.service.mappers;

interface EntityDTOMapper <Entity, DTO> {

    DTO asDTO(Entity entity);

    Entity asEntity(DTO dto);
}
