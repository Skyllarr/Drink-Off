package com.violetbutterfly.drinkoff.service.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyWithUserDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.api.facade.CompanyFacade;
import com.violetbutterfly.drinkoff.persistence.dao.AddressDao;
import com.violetbutterfly.drinkoff.persistence.dao.CompanyDao;
import com.violetbutterfly.drinkoff.persistence.dao.UserDao;
import com.violetbutterfly.drinkoff.persistence.entity.Address;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.service.AuthenticationUtils;
import com.violetbutterfly.drinkoff.service.mappers.AddressMapperService;
import com.violetbutterfly.drinkoff.service.mappers.CompanyMapperService;
import com.violetbutterfly.drinkoff.service.mappers.CompanyWithUserMapperService;
import com.violetbutterfly.drinkoff.service.mappers.UserMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyFacadeImpl implements CompanyFacade {
    @Inject
    private CompanyWithUserMapperService companyWithUserMapper;

    @Inject
    private CompanyMapperService companyMapper;

    @Inject
    private AddressMapperService addressMapper;

    @Inject
    private UserMapperService userMapper;

    @Inject
    private UserDao userDao;

    @Inject
    private AddressDao addressDao;

    @Inject
    private CompanyDao companyDao;


    @Override
    public CompanyWithUserDTO update(CompanyWithUserDTO companyWithUserDTO) {
        return companyWithUserMapper.asDTO(companyDao.update(companyWithUserMapper.asEntity(companyWithUserDTO)));
    }

    @Override
    public CompanyDTO findById(String id) {
        return companyMapper.asDTO(companyDao.findById(id));
    }

    @Override
    public void delete(String id) {
        companyDao.delete(companyDao.findById(id));
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<Company> companyEntities = companyDao.findAll();
        return companyEntities.stream()
                .map(p -> companyMapper.asDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO findByUser(UserDTO userDTO) {
        return companyMapper.asDTO(companyDao.findByUser(userMapper.asEntity(userDTO)));
    }

    @Override
    public CompanyDTO findByName(String name) {
        return companyMapper.asDTO(companyDao.findByName(name));
    }

    @Override
    public CompanyDTO findByIco(String ico) {
        return companyMapper.asDTO(companyDao.findByIco(ico));
    }

    @Override
    public CompanyDTO findByEmail(String email) {
        return companyMapper.asDTO(companyDao.findByUser(userDao.findByEmail(email)));
    }

    @Override
    public void signUpCompany(SignUpCompanyDTO signUpCompanyDTO) throws UserAuthenticationException {
        if (signUpCompanyDTO == null) {
            throw new IllegalArgumentException("Company is null");
        }
        User user = new User(signUpCompanyDTO.getName(), signUpCompanyDTO.getEmail(), signUpCompanyDTO.getPassword(), Role.COMPANY);
        user.setPasswordHash(AuthenticationUtils.createHash(signUpCompanyDTO.getPassword()));
        Company company = new Company();
        company.setName(signUpCompanyDTO.getName());
        company.setDescription(signUpCompanyDTO.getDescription());
        company.setPhoneNumber(signUpCompanyDTO.getPhoneNumber());
        userDao.create(user);
        company.setUser(user);
        Address address = addressMapper.asEntity(signUpCompanyDTO.getAddress());
        addressDao.create(address);
        company.setAddress(address);
        companyDao.create(company);
    }
}
