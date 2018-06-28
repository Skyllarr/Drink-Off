package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.persistence.entity.Discount;
import fr.xebia.extras.selma.Selma;
import org.springframework.stereotype.Service;

@Service
public class DiscountMapperService  extends EntityDTOServiceImpl<Discount, DiscountDTO>{

    private DiscountMapper mapper = Selma.builder(DiscountMapper.class).build();

    @SuppressWarnings("unchecked")
    @Override
    public DiscountMapper getMapper() {
        return mapper;
    }

    @Override
    public DiscountDTO asDTO(Discount discount) {
        DiscountDTO result = mapper.asDTO(discount);
        if (result != null && discount.getCompany() != null) {
                result.setCompanyId(discount.getCompany().getId());
        }
        return result;
    }

    @Override
    public Discount asEntity(DiscountDTO discountDTO) {
        Discount result = mapper.asEntity(discountDTO);
        if (result != null && discountDTO.getCompanyId() != null) {
            Company company = new Company();
            company.setId(discountDTO.getCompanyId());
            result.setCompany(company);
        }
        return result;
    }
}
