package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.entity.AbstractEntity;
import org.testng.Assert;

public class DaoTestUtils {

    public static <T extends AbstractEntity, U extends AbstractDao<T>> void deleteAndFindByIdTest(U abstractDao, T entity) {
        Assert.assertEquals(abstractDao.findById(entity.getId()), entity);
        Assert.assertEquals(abstractDao.findById(entity.getId(), true), entity);
        abstractDao.delete(entity);
        Assert.assertEquals(abstractDao.findById(entity.getId()), null);
        Assert.assertEquals(abstractDao.findById(entity.getId(), true), entity);
        abstractDao.delete(entity, true);
        Assert.assertEquals(abstractDao.findById(entity.getId(), true), null);
    }
}
