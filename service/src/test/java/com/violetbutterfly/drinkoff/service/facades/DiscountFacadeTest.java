package com.violetbutterfly.drinkoff.service.facades;

import com.violetbutterfly.drinkoff.api.dto.AddressDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.api.facade.DiscountFacade;
import com.violetbutterfly.drinkoff.persistence.dao.AddressDao;
import com.violetbutterfly.drinkoff.persistence.dao.CompanyDao;
import com.violetbutterfly.drinkoff.persistence.dao.DiscountDao;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.Discount;
import com.violetbutterfly.drinkoff.service.ObjectsHelper;
import com.violetbutterfly.drinkoff.service.config.ServiceConfig;
import com.violetbutterfly.drinkoff.service.facade.CompanyFacadeImpl;
import com.violetbutterfly.drinkoff.service.facade.DiscountFacadeImpl;
import com.violetbutterfly.drinkoff.service.mappers.AddressMapperService;
import com.violetbutterfly.drinkoff.service.mappers.CompanyMapperService;
import com.violetbutterfly.drinkoff.service.mappers.DiscountMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class DiscountFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AddressMapperService addressMapper;

    @Mock
    private CompanyMapperService companyMapper;

    @Mock
    private DiscountMapperService discountMapper;

    @Mock
    private UserDao userDao;

    @Mock
    private AddressDao addressDao;

    @Mock
    private DiscountDao discountDao;

    @Mock
    private CompanyDao companyDao;

    @InjectMocks
    private DiscountFacade discountFacade = new DiscountFacadeImpl();

    @InjectMocks
    private CompanyFacade companyFacade = new CompanyFacadeImpl();

    @InjectMocks
    private SignUpCompanyDTO signUpCompanyDTO = new SignUpCompanyDTO();

    private Address address;

    private Discount discount;

    private AddressDTO addressDTO;

    private DiscountDTO discountDTO;

    private Company company;

    private CompanyNoCrnDTO companyNoCrnDTO;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        signUpCompanyDTO = ObjectsHelper.getSignUpCompanyDTO();
        company = ObjectsHelper.getCompanyEntity();
        companyNoCrnDTO = ObjectsHelper.getCompanyNoCrnDTO();
        address = ObjectsHelper.getAddressEntity();
        addressDTO = ObjectsHelper.getAddressDTO();
        discountDTO = ObjectsHelper.getDiscountDTO();
        discount = ObjectsHelper.getDiscount();

        when(addressMapper.asEntity(addressDTO)).thenReturn(address);
        when(companyMapper.asDTO(company)).thenReturn(companyNoCrnDTO);
        companyFacade.signUpCompany(signUpCompanyDTO);
    }

    @Test
    public void addDiscountTest() {
        when(discountDao.findAll()).thenReturn(Arrays.asList(discount));
        when(discountMapper.asDTO(discount)).thenReturn(discountDTO);
        discountFacade.create(discountDTO);
        assertThat(discountFacade.findAll().size() == 1);
        assertThat(discountFacade.findAll().get(0)).isEqualTo(discountDTO);
    }

    @Test
    public void findByProductDiscountTest() {
        String product = "beer";
        when(discountDao.findByProduct(product)).thenReturn(Arrays.asList(discount));
        when(discountMapper.asDTO(discount)).thenReturn(discountDTO);
        discountFacade.create(discountDTO);
        assertThat(discountFacade.findByProduct(product).size() == 1);
        assertThat(discountFacade.findByProduct(product).get(0)).isEqualTo(discountDTO);
    }
}
