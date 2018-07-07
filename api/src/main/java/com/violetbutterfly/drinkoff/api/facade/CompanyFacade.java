package com.violetbutterfly.drinkoff.api.facade;

import com.violetbutterfly.drinkoff.api.dto.CompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.CompanyWithUserDTO;
import com.violetbutterfly.drinkoff.api.dto.SignUpCompanyDTO;
import com.violetbutterfly.drinkoff.api.dto.UserDTO;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;

import java.util.List;

public interface CompanyFacade {

    CompanyWithUserDTO update(CompanyWithUserDTO company);

    CompanyDTO findById(String id);

    void delete(String id);

    List<CompanyDTO> findAll();

    CompanyDTO findByUser(UserDTO userDTO);

    CompanyDTO findByName(String name);

    CompanyDTO findByEmail(String email);

    CompanyDTO findByIco(String ico);

    void signUpCompany(SignUpCompanyDTO signUpCompanyDTO) throws UserAuthenticationException;
}
