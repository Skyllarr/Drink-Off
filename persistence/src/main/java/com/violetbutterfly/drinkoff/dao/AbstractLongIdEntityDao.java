package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.AbstractLongIdEntity;

public class AbstractLongIdEntityDao<T extends AbstractLongIdEntity> extends AbstractDao<Long, T>{

    AbstractLongIdEntityDao(Class<T> clazz) {
        super(clazz);
    }

}
