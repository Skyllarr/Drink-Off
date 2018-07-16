package com.violetbutterfly.drinkoff.service.mappers;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.persistence.entity.Company;
import com.violetbutterfly.drinkoff.service.ObjectsHelper;
import com.violetbutterfly.drinkoff.service.config.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNull;

@ContextConfiguration(classes = ServiceConfig.class)
public class CompanyMapperTest extends AbstractTestNGSpringContextTests {
    @Inject
    private CompanyWithUserMapperService companyWithUserMapper;

    private Company entity;
    private CompanyDTO dto;

    @BeforeClass
    public void prepareData() {
        entity = ObjectsHelper.getCompanyEntity();
        dto = ObjectsHelper.getCompanyWithUserDTO();
    }

    @Test
    public void mapDto() {
        CompanyDTO result = companyWithUserMapper.asDTO(entity);
        assertThat(result.getUrl()).isEqualTo(dto.getUrl());
        assertThat(result.getId()).isEqualTo(dto.getId());
        assertThat(result.getCrn()).isEqualTo(dto.getCrn());
        assertThat(result.getName()).isEqualTo(dto.getName());
        assertThat(result.getDescription()).isEqualTo(dto.getDescription());
        assertThat(result.getPhoneNumber()).isEqualTo(dto.getPhoneNumber());
        assertThat(result.getUser().getEmail()).isEqualTo(dto.getUser().getEmail());
        assertThat(result.getUrl()).isEqualTo(dto.getUrl());

    }

    @Test
    public void mapNullDto() {
        assertNull(companyWithUserMapper.asDTO(null));
    }

    @Test
    public void mapEntity() {
        Company result = companyWithUserMapper.asEntity(dto);
        assertThat(result).isEqualToComparingFieldByField(entity);
    }

    @Test
    public void mapNullEntity() {
        assertNull(companyWithUserMapper.asEntity(null));
    }
}
