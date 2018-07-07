package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyWithUserDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompanyWithUserMapperService extends EntityDTOServiceImpl<Company, CompanyWithUserDTO> {

    private CompanyWithUserMapper mapper = Selma.builder(CompanyWithUserMapper.class).build();

    @SuppressWarnings("unchecked")
    @Override
    public CompanyWithUserMapper getMapper() {
        return mapper;
    }

    @Inject
    private UserMapperService userMapper;

    @Inject
    private AddressMapperService addressMapper;

    @Override
    public CompanyWithUserDTO asDTO(Company company) {
        CompanyWithUserDTO result = mapper.asDTO(company);
        if (result != null && company.getUser() != null) {
            result.setUser(userMapper.asDTO(company.getUser()));
        }
        if (result != null && company.getAddress() != null) {
            result.setAddress(addressMapper.asDTO(company.getAddress()));
        }
        return result;
    }

    @Override
    public Company asEntity(CompanyWithUserDTO companyWithUserDTO) {
        Company result = mapper.asEntity(companyWithUserDTO);
        if (result != null) {
            if (companyWithUserDTO.getUser() != null) {
                result.setUser(userMapper.asEntity(companyWithUserDTO.getUser()));
            }
            if (companyWithUserDTO.getAddress() != null) {
                result.setAddress(addressMapper.asEntity(companyWithUserDTO.getAddress()));
            }
        }
        return result;
    }
}
