package com.violetbutterfly.drinkoff.service.facades;

import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.api.facade.UserFacade;
import com.violetbutterfly.drinkoff.persistence.dao.AddressDao;
import com.violetbutterfly.drinkoff.persistence.dao.CompanyDao;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.service.ObjectsHelper;
import com.violetbutterfly.drinkoff.service.UserService;
import com.violetbutterfly.drinkoff.service.UserServiceImpl;
import com.violetbutterfly.drinkoff.service.config.ServiceConfig;
import com.violetbutterfly.drinkoff.service.facade.CompanyFacadeImpl;
import com.violetbutterfly.drinkoff.service.facade.UserFacadeImpl;
import com.violetbutterfly.drinkoff.service.mappers.AddressMapperService;
import com.violetbutterfly.drinkoff.service.mappers.UserMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceConfig.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AddressMapperService addressMapper;

    @Mock
    private UserMapperService userMapper;

    @Mock
    private UserDao userDao;

    @Mock
    private AddressDao addressDao;

    @Mock
    private CompanyDao companyDao;

    @Mock
    private UserService userService = new UserServiceImpl();

    @InjectMocks
    private UserFacade userFacade = new UserFacadeImpl();

    @InjectMocks
    private CompanyFacade companyFacade = new CompanyFacadeImpl();

    @InjectMocks
    private SignUpCompanyDTO signUpCompanyDTO = new SignUpCompanyDTO();

    private UserDTO userDTO;

    private User user;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        signUpCompanyDTO = ObjectsHelper.getSignUpCompanyDTO();
        user = ObjectsHelper.getUserEntity();
        userDTO = ObjectsHelper.getUserDTO();
        companyFacade.signUpCompany(signUpCompanyDTO);
    }

    @Test
    public void authenticateUserTest() {
        when(userMapper.asEntity(userDTO)).thenReturn(user);
        when(userService.authenticate(user, "password")).thenReturn(true);
        when(userService.authenticate(user, "invalid_password")).thenReturn(false);
        assertTrue(userFacade.authenticate(userDTO, "password"));
        assertFalse(userFacade.authenticate(userDTO, "invalid_password"));
    }
}
