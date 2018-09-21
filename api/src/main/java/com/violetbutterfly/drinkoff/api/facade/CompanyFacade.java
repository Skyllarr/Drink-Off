package com.violetbutterfly.drinkoff.api.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyNoCrnDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;

import java.util.List;

public interface CompanyFacade {

    CompanyDTO update(CompanyDTO company);

    CompanyNoCrnDTO findById(String id);

    CompanyNoCrnDTO findByUserId(String id);

    CompanyDTO getMyInfo(String id);

    void delete(String id);

    List<CompanyNoCrnDTO> findAll();

    CompanyNoCrnDTO findByUser(UserDTO userDTO);

    CompanyNoCrnDTO findByName(String name);

    CompanyNoCrnDTO findByEmail(String email);

    CompanyNoCrnDTO findByIco(String ico);

    void signUpCompany(SignUpCompanyDTO signUpCompanyDTO) throws UserAuthenticationException;
}
