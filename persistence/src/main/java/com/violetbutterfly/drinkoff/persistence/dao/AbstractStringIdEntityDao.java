package com.violetbutterfly.drinkoff.persistence.dao;

import com.violetbutterfly.drinkoff.persistence.entity.AbstractStringIdEntity;

public class AbstractStringIdEntityDao<T extends AbstractStringIdEntity> extends AbstractDao<String, T>{

    AbstractStringIdEntityDao(Class<T> clazz) {
        super(clazz);
    }
}
