package com.violetbutterfly.drinkoff.service.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.DiscountDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.facade.DiscountFacade;
import com.violetbutterfly.drinkoff.persistence.dao.DiscountDao;
import com.violetbutterfly.drinkoff.persistence.entity.Discount;
import com.violetbutterfly.drinkoff.service.mappers.CompanyMapperService;
import com.violetbutterfly.drinkoff.service.mappers.DiscountMapperService;
import com.violetbutterfly.drinkoff.service.mappers.UserMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Discount> discountEntities = discountDao.findAll();
        return discountEntities.stream()
                .map(p -> discountMapper.asDTO(p))
                .collect(Collectors.toList());
    }

    public DiscountDTO findById(String id) {
        return discountMapper.asDTO(discountDao.findById(id));
    }

    public List<DiscountDTO> findByCompany(CompanyDTO company) {
        List<Discount> discountEntities = discountDao.findByCompany(companyMapper.asEntity(company));
        return discountEntities.stream()
                .map(p -> discountMapper.asDTO(p))
                .collect(Collectors.toList());
    }

    public List<DiscountDTO> findByProduct(String product) {
        List<Discount> discountEntities = discountDao.findByProduct(product);
        return discountEntities.stream()
                .map(p -> discountMapper.asDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByUser(UserDTO user) {
        List<Discount> discountEntities = discountDao.findByUser(userMapper.asEntity(user));
        return discountEntities.stream()
                .map(p -> discountMapper.asDTO(p))
                .collect(Collectors.toList());
    }
}
