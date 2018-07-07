package com.violetbutterfly.drinkoff.service.services;

import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.persistence.dao.AddressDao;
import com.violetbutterfly.drinkoff.persistence.dao.CompanyDao;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.service.ObjectsHelper;
import com.violetbutterfly.drinkoff.service.UserService;
import com.violetbutterfly.drinkoff.service.UserServiceImpl;
import com.violetbutterfly.drinkoff.service.config.ServiceConfig;
import com.violetbutterfly.drinkoff.service.facade.CompanyFacadeImpl;
import com.violetbutterfly.drinkoff.service.mappers.AddressMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @Mock
    private AddressDao addressDao;

    @Mock
    private CompanyDao companyDao;

    @Mock
    private AddressMapperService addressMapper;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @InjectMocks
    private CompanyFacade companyFacade = new CompanyFacadeImpl();

    @InjectMocks
    private SignUpCompanyDTO signUpCompanyDTO = new SignUpCompanyDTO();

    private User user;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        signUpCompanyDTO = ObjectsHelper.getSignUpCompanyDTO();
        user = ObjectsHelper.getUserEntity();
        companyFacade.signUpCompany(signUpCompanyDTO);
    }

    @Test
    public void authenticateUserTest() {
        assertTrue(userService.authenticate(user, "password"));
        assertFalse(userService.authenticate(user, "invalid_password"));
    }

    @Test
    public void changePasswordTest() {
        userService.changePassword(user, "password", "new_password");
        assertTrue(userService.authenticate(user, "new_password"));
        assertFalse(userService.authenticate(user, "password"));
    }
}
