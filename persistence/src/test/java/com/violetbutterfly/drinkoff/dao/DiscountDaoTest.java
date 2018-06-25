package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.PersistenceApplicationContext;
import com.violetbutterfly.drinkoff.entity.Address;
import com.violetbutterfly.drinkoff.entity.Company;
import com.violetbutterfly.drinkoff.entity.Discount;
import com.violetbutterfly.drinkoff.entity.User;
import com.violetbutterfly.drinkoff.enums.Role;
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
public class DiscountDaoTest extends AbstractTestNGSpringContextTests {
    @Inject
    private DiscountDao discountDao;

    @Inject
    private CompanyDao companyDao;

    @Inject
    private AddressDao addressDao;

    @Inject
    private UserDao userDao;

    private Discount discount;
    private Company company;
    private Address address;
    private User user;

    @BeforeMethod
    public void init() {
        createTestObjects();
    }

    @Test
    public void findAllAddressesTest() {
        Assert.assertEquals(discountDao.findAll().size(), 1);
        Discount tempDiscount = getDiscount();
        List<Discount> foundCompanies = discountDao.findAll();
        Assert.assertEquals(foundCompanies.size(), 2);
        Assert.assertTrue(foundCompanies.contains(discount));
        Assert.assertTrue(foundCompanies.contains(tempDiscount));
    }

    @Test
    public void findDiscountByIdTest() {
        DaoTestUtils.deleteAndFindByIdTest(discountDao, discount);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void productNullTest() {
        discount.setProduct(null);
        discountDao.update(discount);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void companyNullTest() {
        discount.setCompany(null);
        discountDao.update(discount);
    }

    @Test
    public void createDiscountTest() {
        Discount tempDiscount = getDiscount();
        discountDao.create(tempDiscount);
        Discount foundDiscount = discountDao.findById(tempDiscount.getId());
        assertThat(foundDiscount, new ReflectionEquals(tempDiscount));
    }

    @Test
    public void editDiscountTest() {
        discount.setProduct("EditedProduct");
        discountDao.update(discount);
        Discount foundDiscount = discountDao.findById(discount.getId());
        assertThat(foundDiscount, new ReflectionEquals(discount));
    }

    @Test
    public void findDiscountByProductTest() {
        Assert.assertEquals(discountDao.findByProduct("beer").size(), 1);
        createTempDiscounts();
        Assert.assertEquals(discountDao.findByProduct("beer").size(), 2);
    }

    @Test
    public void findDiscountByCompanyTest() {
        Assert.assertEquals(discountDao.findByCompany(company).size(), 1);
        createTempDiscounts();
        Assert.assertEquals(discountDao.findByCompany(company).size(), 3);
    }

    private void createTempDiscounts() {
        Discount tempDiscount = new Discount(company, "beer");
        Discount tempDiscount2 = new Discount(company, "milkshake");
        discountDao.create(tempDiscount);
        discountDao.create(tempDiscount2);
    }

    private void createTestObjects() {
        user = new User();
        user.setDeleted(false);
        user.setNick("user1");
        user.setEmail("user1@example.com");
        user.setType(Role.ADMIN);
        user.setPasswordHash("passHash");
        userDao.create(user);

        address = new Address();
        address.setCity("Praha");
        address.setStreet("Prazska");
        address.setHouseNumber("46");
        address.setCountry("Czech_republic");
        address.setZipcode("48521");
        address.setState("Czech_republic");
        addressDao.create(address);

        company = new Company();
        company.setDeleted(false);
        company.setUser(user);
        company.setAddress(address);
        company.setPhoneNumber("+421 080 584");
        company.setUser(user);
        company.setAddress(address);
        company.setName("companyName");
        companyDao.create(company);

        discount = new Discount();
        discount.setProduct("Beer");
        discount.setCompany(company);
        discountDao.create(discount);
    }

    private Discount getDiscount() {
        Company tempCompany = new Company();
        User user2 = new User();
        user2.setDeleted(false);
        user2.setNick("user2");
        user2.setEmail("user2@example.com");
        user2.setType(Role.PLAYER);
        user2.setPasswordHash("passHash");
        userDao.create(user2);
        tempCompany.setUser(user2);
        tempCompany.setAddress(address);
        tempCompany.setPhoneNumber("58545");
        tempCompany.setName("tempCompanyName");
        companyDao.create(tempCompany);

        Discount tempDiscount = new Discount();
        tempDiscount.setProduct("dinner");
        tempDiscount.setCompany(tempCompany);
        discountDao.create(tempDiscount);
        return tempDiscount;
    }
}
