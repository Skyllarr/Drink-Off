package com.violetbutterfly.drinkoff.service.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.DiscountFacade;
import com.violetbutterfly.drinkoff.persistence.dao.DiscountDao;
import com.violetbutterfly.drinkoff.service.mappers.CompanyMapperService;
import com.violetbutterfly.drinkoff.service.mappers.DiscountMapperService;
import com.violetbutterfly.drinkoff.service.mappers.UserMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DiscountFacadeImpl implements DiscountFacade {
    @Inject
    private DiscountDao discountDao;

    @Inject
    private DiscountMapperService discountMapper;

    @Inject
    private CompanyMapperService companyMapper;

    @Inject
    private UserMapperService userMapper;

    public void create(DiscountDTO discount) {
        discountDao.create(discountMapper.asEntity(discount));
    }

    public DiscountDTO update(DiscountDTO discount) {
        return discountMapper.asDTO(discountDao.update(discountMapper.asEntity(discount)));
    }

    public void delete(String id) {
        discountDao.delete(discountDao.findById(id));
    }

    public List<DiscountDTO> findAll() {
        return discountMapper.asDtos(discountDao.findAll());
    }

    public DiscountDTO findById(String id) {
        return discountMapper.asDTO(discountDao.findById(id));
    }

    public List<DiscountDTO> findByCompany(CompanyNoCrnDTO company) {
        return discountMapper.asDtos(discountDao.findByCompany(companyMapper.asEntity(company)));
    }

    public List<DiscountDTO> findByProduct(String product) {
        return discountMapper.asDtos(discountDao.findByProduct(product));
    }

    @Override
    public List<DiscountDTO> getDiscounts(UserDTO user) {
        return discountMapper.asDtos(discountDao.findByUser(userMapper.asEntity(user)));
    }
}
