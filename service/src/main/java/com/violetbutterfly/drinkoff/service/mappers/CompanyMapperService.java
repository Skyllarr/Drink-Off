package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompanyMapperService extends EntityDTOServiceImpl<Company, CompanyNoCrnDTO> {

    private CompanyMapper mapper = Selma.builder(CompanyMapper.class).build();
    @Inject
    private AddressMapperService addressMapper;

    @SuppressWarnings("unchecked")
    @Override
    public CompanyMapper getMapper() {
        return mapper;
    }

    @Override
    public CompanyNoCrnDTO asDTO(Company company) {
        CompanyNoCrnDTO result = mapper.asDTO(company);
        if (result != null && company.getUser() != null) {
            result.setUserId(company.getUser().getId());
        }
        if (result != null && company.getAddress() != null) {
            result.setAddress(addressMapper.asDTO(company.getAddress()));
        }
        return result;
    }

    @Override
    public Company asEntity(CompanyNoCrnDTO companyNoCrnDTO) {
        Company result = mapper.asEntity(companyNoCrnDTO);
        if (result != null) {
            if (companyNoCrnDTO.getUserId() != null) {
                User user = new User();
                user.setId(companyNoCrnDTO.getUserId());
                result.setUser(user);
            }
            if (companyNoCrnDTO.getAddress() != null) {
                result.setAddress(addressMapper.asEntity(companyNoCrnDTO.getAddress()));
            }
        }
        return result;
    }
}
