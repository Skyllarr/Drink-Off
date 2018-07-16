package com.violetbutterfly.drinkoff.service.facades;

import com.violetbutterfly.drinkoff.api.dto.AddressDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.persistence.dao.AddressDao;
import com.violetbutterfly.drinkoff.persistence.dao.CompanyDao;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.service.ObjectsHelper;
import com.violetbutterfly.drinkoff.service.config.ServiceConfig;
import com.violetbutterfly.drinkoff.service.facade.CompanyFacadeImpl;
import com.violetbutterfly.drinkoff.service.mappers.AddressMapperService;
import com.violetbutterfly.drinkoff.service.mappers.CompanyMapperService;
import com.violetbutterfly.drinkoff.service.mappers.CompanyWithUserMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class CompanyFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AddressMapperService addressMapper;

    @Mock
    private CompanyMapperService companyMapper;

    @Mock
    private CompanyWithUserMapperService companyWithUserMapper;

    @Mock
    private UserDao userDao;

    @Mock
    private AddressDao addressDao;

    @Mock
    private CompanyDao companyDao;

    @InjectMocks
    private CompanyFacade companyFacade = new CompanyFacadeImpl();

    @InjectMocks
    private SignUpCompanyDTO signUpCompanyDTO = new SignUpCompanyDTO();

    private Address address;

    private AddressDTO addressDTO;

    private Company company;

    private CompanyNoCrnDTO companyNoCrnDTO;

    private CompanyDTO companyDTO;

    private User user;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        signUpCompanyDTO = ObjectsHelper.getSignUpCompanyDTO();
        company = ObjectsHelper.getCompanyEntity();
        companyNoCrnDTO = ObjectsHelper.getCompanyNoCrnDTO();
        address = ObjectsHelper.getAddressEntity();
        user = ObjectsHelper.getUserEntity();
        addressDTO = ObjectsHelper.getAddressDTO();
        companyDTO = ObjectsHelper.getCompanyWithUserDTO();

        when(addressMapper.asEntity(addressDTO)).thenReturn(address);
        when(companyMapper.asDTO(company)).thenReturn(companyNoCrnDTO);
    }

    @Test
    public void signUpTest() {
        companyFacade.signUpCompany(signUpCompanyDTO);
        when(companyDao.findAll()).thenReturn(Arrays.asList(company));
        List<CompanyNoCrnDTO> result = companyFacade.findAll();
        assertThat(result.size() == 1);
        assertThat(result.get(0)).isEqualTo(companyNoCrnDTO);
    }

    @Test
    public void findCompanyByNameTest() {
        companyFacade.signUpCompany(signUpCompanyDTO);
        when(companyDao.findByName(signUpCompanyDTO.getName())).thenReturn(company);
        CompanyNoCrnDTO result = companyFacade.findByName(signUpCompanyDTO.getName());
        assertThat(result).isEqualTo(companyNoCrnDTO);
    }

    @Test
    public void findCompanyByEmailTest() {
        companyFacade.signUpCompany(signUpCompanyDTO);
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        when(companyDao.findByUser(user)).thenReturn(company);
        CompanyNoCrnDTO result = companyFacade.findByEmail(signUpCompanyDTO.getEmail());
        assertThat(result).isEqualTo(companyNoCrnDTO);
    }

    @Test
    public void updateCompanyTest() {
        companyFacade.signUpCompany(signUpCompanyDTO);
        String newUrl = "new_url";
        companyDTO.setUrl(newUrl);
        companyNoCrnDTO.setUrl(newUrl);
        company.setUrl(newUrl);
        when(companyDao.findByName(signUpCompanyDTO.getName())).thenReturn(company);
        when(companyMapper.asDTO(company)).thenReturn(companyNoCrnDTO);
        when(companyWithUserMapper.asDTO(company)).thenReturn(companyDTO);
        when(companyWithUserMapper.asEntity(companyDTO)).thenReturn(company);
        when(companyDao.update(company)).thenReturn(company);
        CompanyDTO updatedResult = companyFacade.update(companyDTO);
        assertThat(updatedResult.getUrl()).isEqualTo(newUrl);
        CompanyNoCrnDTO result = companyFacade.findByName(signUpCompanyDTO.getName());
        assertThat(result.getUrl()).isEqualTo(newUrl);
    }
}
