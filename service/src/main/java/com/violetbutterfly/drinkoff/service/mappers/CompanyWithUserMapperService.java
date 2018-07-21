package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompanyWithUserMapperService extends EntityDTOServiceImpl<Company, CompanyDTO> {

    private CompanyWithUserMapper mapper = Selma.builder(CompanyWithUserMapper.class).build();
    @Inject
    private UserMapperService userMapper;
    @Inject
    private AddressMapperService addressMapper;

    @SuppressWarnings("unchecked")
    @Override
    public CompanyWithUserMapper getMapper() {
        return mapper;
    }

    @Override
    public CompanyDTO asDTO(Company company) {
        CompanyDTO result = mapper.asDTO(company);
        if (result != null && company.getUser() != null) {
            result.setUser(userMapper.asDTO(company.getUser()));
        }
        if (result != null && company.getAddress() != null) {
            result.setAddress(addressMapper.asDTO(company.getAddress()));
        }
        return result;
    }

    @Override
    public Company asEntity(CompanyDTO companyDTO) {
        Company result = mapper.asEntity(companyDTO);
        if (result != null) {
            if (companyDTO.getUser() != null) {
                result.setUser(userMapper.asEntity(companyDTO.getUser()));
            }
            if (companyDTO.getAddress() != null) {
                result.setAddress(addressMapper.asEntity(companyDTO.getAddress()));
            }
        }
        return result;
    }
}
