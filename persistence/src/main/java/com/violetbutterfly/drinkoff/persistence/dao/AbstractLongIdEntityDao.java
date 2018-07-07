package com.violetbutterfly.drinkoff.persistence.dao;

import com.violetbutterfly.drinkoff.persistence.entity.AbstractLongIdEntity;

public class AbstractLongIdEntityDao<T extends AbstractLongIdEntity> extends AbstractDao<Long, T>{

    AbstractLongIdEntityDao(Class<T> clazz) {
        super(clazz);
    }

}
