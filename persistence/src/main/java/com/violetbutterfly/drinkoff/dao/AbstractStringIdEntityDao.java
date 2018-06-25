package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.AbstractStringIdEntity;

public class AbstractStringIdEntityDao<T extends AbstractStringIdEntity> extends AbstractDao<String, T>{

    AbstractStringIdEntityDao(Class<T> clazz) {
        super(clazz);
    }
}
