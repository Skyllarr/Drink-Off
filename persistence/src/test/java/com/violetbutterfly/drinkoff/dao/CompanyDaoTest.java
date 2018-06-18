package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.PersistenceApplicationContext;
import com.violetbutterfly.drinkoff.entity.Address;
import com.violetbutterfly.drinkoff.entity.Company;
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
public class CompanyDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CompanyDao companyDao;

    @Inject
    private UserDao userDao;

    @Inject
    private AddressDao addressDao;

    private String phoneNumber = "+421 080 584";
    private Company company;
    private Address address;
    private User user1;
    private User user2;

    @BeforeMethod
    public void init() {
        createTestObjects();
    }

    @Test
    public void findAllCompaniesTest() {
        Assert.assertTrue(companyDao.findAll().size() == 1);
        Assert.assertTrue(companyDao.findAll().get(0).equals(company));
        Company tempCompany = createCompany();
        List<Company> foundCompanies = companyDao.findAll();
        Assert.assertTrue(foundCompanies.size() == 2);
        Assert.assertTrue(foundCompanies.contains(company));
        Assert.assertTrue(foundCompanies.contains(tempCompany));
    }

    @Test
    public void findCompanyByIdTest() {
        DaoTestUtils.deleteAndFindByIdTest(companyDao, company);
    }

    @Test
    public void createCompanyTest() {
        Company tempCompany = createCompany();
        Company foundCompany = companyDao.findById(tempCompany.getId());
        assertThat(foundCompany, new ReflectionEquals(tempCompany));
    }

    @Test
    public void editCompanyTest() {
        userDao.create(user2);
        company.setUser(user2);
        companyDao.update(company);
        Company foundCompany = companyDao.findById(company.getId());
        assertThat(foundCompany, new ReflectionEquals(company));
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void phoneNumberTooLongTest() {
        company.setPhoneNumber("PhoneNumber Too Loooooooooooooooooooooooooooooooong");
        companyDao.update(company);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void phoneNumberNullTest() {
        company.setPhoneNumber(null);
        companyDao.update(company);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void userNullTest() {
        company.setUser(null);
        companyDao.update(company);
    }

    @Test
    public void findCompanyByUserTest() {
        Assert.assertEquals(companyDao.findByUser(user1).get(0), company);
    }

    @Test
    public void findCompanyByAddressIdTest() {
        Assert.assertEquals(companyDao.findByAddressId(address), company);
    }

    @Test
    public void findCompanyByPhoneNumber() {
        Company found = companyDao.findByPhoneNumber(phoneNumber);
        Assert.assertTrue(found.equals(company));
    }

    @Test
    public void findCompaniesByState() {
        List<Company> found = companyDao.findByState(company.getAddress().getState());
        Assert.assertTrue(found.stream().allMatch(c -> c.getAddress().getState().contains(company.getAddress().getState())));
    }

    @Test
    public void findCompaniesByCountry() {
        List<Company> found = companyDao.findByCountry(company.getAddress().getCountry());
        Assert.assertTrue(found.stream().allMatch(c -> c.getAddress().getCountry().contains(company.getAddress().getCountry())));
    }

    @Test
    public void findCompaniesByCity() {
        List<Company> found = companyDao.findByCity(company.getAddress().getCity());
        Assert.assertTrue(found.stream().allMatch(c -> c.getAddress().getCity().contains(company.getAddress().getCity())));
    }

    private void createTestObjects() {
        user1 = new User();
        user1.setDeleted(false);
        user1.setNick("user1");
        user1.setEmail("user1@example.com");
        user1.setType(Role.ADMIN);
        user1.setPasswordHash("passHash");
        userDao.create(user1);

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
        company.setUser(user1);
        company.setAddress(address);
        company.setPhoneNumber(phoneNumber);
        company.setUser(user1);
        company.setAddress(address);
        companyDao.create(company);

        user2 = new User();
        user2.setDeleted(false);
        user2.setNick("user2");
        user2.setEmail("user2@example.com");
        user2.setType(Role.PLAYER);
        user2.setPasswordHash("passHash");
        userDao.create(user2);
    }

    private Company createCompany() {
        Company tempCompany = new Company();
        tempCompany.setUser(user2);
        tempCompany.setAddress(address);
        tempCompany.setPhoneNumber("58545");
        companyDao.create(tempCompany);
        return tempCompany;
    }
}
