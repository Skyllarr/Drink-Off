package com.violetbutterfly.drinkoff.dao;

import com.violetbutterfly.drinkoff.PersistenceApplicationContext;
import com.violetbutterfly.drinkoff.entity.Address;
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
public class AddressDaoTest extends AbstractTestNGSpringContextTests {
    @Inject
    private AddressDao addressDao;

    private Address address;

    @BeforeMethod
    public void init() {
        createTestAddress();
    }

    @Test
    public void findAllAddressesTest() {
        Assert.assertEquals(addressDao.findAll().size(), 1);

        Address tempAddress = createAddress();
        addressDao.create(tempAddress);

        List<Address> foundCompanies = addressDao.findAll();
        Assert.assertEquals(foundCompanies.size(), 2);
        Assert.assertTrue(foundCompanies.contains(address));
        Assert.assertTrue(foundCompanies.contains(tempAddress));
    }

    @Test
    public void findAddressByIdTest() {
        DaoTestUtils.deleteAndFindByIdTest(addressDao, address);
    }

    @Test
    public void createAddressTest() {
        Address tempAddress = createAddress();
        Address foundAddress = addressDao.findById(tempAddress.getId());
        assertThat(foundAddress, new ReflectionEquals(tempAddress));
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void houseNumberTooLongTest() {
        address.setHouseNumber("CityTooooooooooooooooooooooooooooooooooooooooooooooooooLong");
        addressDao.update(address);
    }

    @Test(expectedExceptions = {JpaSystemException.class})
    public void cityNullTest() {
        address.setCity(null);
        addressDao.update(address);
    }

    @Test
    public void editAddressTest() {
        address.setCity("EditedCity");
        addressDao.update(address);
        Address foundAddress = addressDao.findById(address.getId());
        assertThat(foundAddress, new ReflectionEquals(address));
    }

    @Test
    public void findAddressByCityTest() {
        Assert.assertEquals(addressDao.findByCity("Dublin").size(), 1);
        createTestAddresses();
        Assert.assertEquals(addressDao.findByCity("Dublin").size(), 2);
    }

    @Test
    public void findAddressByStateTest() {
        Assert.assertEquals(addressDao.findByCountry("Ireland").size(), 1);
        createTestAddresses();
        Assert.assertEquals(addressDao.findByCountry("Ireland").size(), 2);
    }

    @Test
    public void findAddressByCounty() {
        Assert.assertEquals(addressDao.findByState("Cornwall").size(), 0);
        createTestAddresses();
        Assert.assertEquals(addressDao.findByState("Cornwall").size(), 2);
    }

    private void createTestAddress() {
        address = new Address();
        address.setCity("Dublin");
        address.setStreet("Dublin Street");
        address.setHouseNumber("46");
        address.setCountry("Ireland");
        address.setZipcode("48521");
        address.setState("county");
        addressDao.create(address);
    }

    private void createTestAddresses() {
        Address tempAddress = new Address("Hrnciarska", "54", "Brno", "Cornwall", "Czech Republic", "58564");
        Address tempAddress2 = new Address("Dublin Street", "77", "Dublin", "Cornwall", "Ireland", "58784");
        addressDao.create(tempAddress);
        addressDao.create(tempAddress2);
    }

    private Address createAddress() {
        Address temp = new Address("Diagonal Alley", "52", "Montreal", "Canada County", "Canada", "478541");
        addressDao.create(temp);
        return temp;
    }
}
