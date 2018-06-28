package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompanyMapperService extends EntityDTOServiceImpl<Company, CompanyDTO> {

    private CompanyMapper mapper = Selma.builder(CompanyMapper.class).build();

    @SuppressWarnings("unchecked")
    @Override
    public CompanyMapper getMapper() {
        return mapper;
    }

    @Inject
    private AddressMapperService addressMapper;

    @Override
    public CompanyDTO asDTO(Company company) {
        CompanyDTO result = mapper.asDTO(company);
        if (result != null && company.getUser() != null) {
            result.setUserId(company.getUser().getId());
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
            if (companyDTO.getUserId() != null) {
                User user = new User();
                user.setId(companyDTO.getUserId());
                result.setUser(user);
            }
            if (companyDTO.getAddress() != null) {
                result.setAddress(addressMapper.asEntity(companyDTO.getAddress()));
            }
        }
        return result;
    }
}
