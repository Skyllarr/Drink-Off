package com.violetbutterfly.drinkoff.persistence.dao;

import com.violetbutterfly.drinkoff.persistence.PersistenceApplicationContext;
import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    @Inject
    private UserDao userDao;

    private User user;

    @BeforeMethod
    public void init() {
        createTestObjects();
    }

    @Test
    public void findAllAddressesTest() {
        Assert.assertEquals(userDao.findAll().size(), 1);
        User tempUser = getUser();
        userDao.create(tempUser);
        List<User> foundCompanies = userDao.findAll();
        Assert.assertEquals(foundCompanies.size(), 2);
        Assert.assertTrue(foundCompanies.contains(user));
        Assert.assertTrue(foundCompanies.contains(tempUser));
    }

    @Test
    public void findUserByIdTest() {
        DaoTestUtils.deleteAndFindByIdTest(userDao, user);
    }

    @Test
    public void createUserTest() {
        User tempUser = getUser();
        User foundAddress = userDao.findById(tempUser.getId());
        assertThat(foundAddress, new ReflectionEquals(tempUser));
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void productNullTest() {
        user.setEmail(null);
        userDao.update(user);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void companyNullTest() {
        user.setNick(null);
        userDao.update(user);
    }

    @Test
    public void editUserTest() {
        user.setEmail("editedEmail@example.com");
        userDao.update(user);
        User foundUser = userDao.findById(user.getId());
        assertThat(foundUser, new ReflectionEquals(user));
    }

    @Test
    public void findUserByEmailTest() {
        Assert.assertEquals(userDao.findByEmail("user@example.com"), user);
    }

    @Test
    public void findUserByNickTest() {
        User tempUser = new User("userNick", "userEmail@example.com", "passHash", Role.PLAYER);
        User tempUser2 = new User("user2Nick", "user2Email@example.com", "passHash2", Role.COMPANY);
        userDao.create(tempUser);
        userDao.create(tempUser2);
        Assert.assertEquals(userDao.findByNick("userNick").size(), 1);
    }

    private void createTestObjects() {
        user = new User();
        user.setDeleted(false);
        user.setNick("user");
        user.setEmail("user@example.com");
        user.setType(Role.PLAYER);
        user.setPasswordHash("passHash");
        userDao.create(user);
    }

    private User getUser() {
        User tempUser = new User();
        tempUser.setDeleted(false);
        tempUser.setNick("tempUser");
        tempUser.setEmail("tempUser@example.com");
        tempUser.setType(Role.ADMIN);
        tempUser.setPasswordHash("agbeaarf");
        userDao.create(tempUser);
        return tempUser;
    }
}
