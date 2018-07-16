package com.violetbutterfly.drinkoff.web.security;

import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import com.violetbutterfly.drinkoff.persistence.entity.User;
import com.violetbutterfly.drinkoff.service.UserService;
import com.violetbutterfly.drinkoff.web.error.ForbiddenAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class AccountAuthenticationProvider implements AuthenticationProvider {
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private static final String NOT_AUTHORIZED = "User trying to access client type they are not authorized for.";

    @Inject
    private UserService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = authService.findByEmail(email);
        if (user == null) {
            throw new UserAuthenticationException(INVALID_CREDENTIALS);
        }
        if (!authService.authenticate(user, password)) {
            throw new UserAuthenticationException(INVALID_CREDENTIALS);
        }

        if (user.getType() != Role.ADMIN) {
            //exception translations from oauth2 to spring security along with its handlers are in DefaultWebResponseExceptionTranslator
            ClientType clientType = getClientType();
            switch (clientType == null ? ClientType.UNKNOWN : clientType) {
                case COMPANY_MOBILE:
                    if (user.getType() != Role.COMPANY)
                        throw new ForbiddenAccessException(NOT_AUTHORIZED);
                    break;
                case PLAYER_MOBILE:
                    if (user.getType() != Role.PLAYER)
                        throw new ForbiddenAccessException(NOT_AUTHORIZED);
                    break;
                case COMPANY_WEB:
                    if (user.getType() != Role.COMPANY)
                        throw new ForbiddenAccessException(NOT_AUTHORIZED);
                    break;
                default:
                    throw new ForbiddenAccessException(NOT_AUTHORIZED);
            }
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getType().name()));
        return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private ClientType getClientType() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String authorizationHeaderValue = request.getHeader("Authorization");
        final String base64AuthorizationHeader = Optional.ofNullable(authorizationHeaderValue)
                .map(headerValue -> headerValue.substring("Basic ".length())).orElse("");

        if (!StringUtils.isEmpty(base64AuthorizationHeader)) {
            String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(base64AuthorizationHeader), Charset.forName("UTF-8"));
            String clientType = decodedAuthorizationHeader.split(":")[0];
            String clientSecret = decodedAuthorizationHeader.split(":")[1];
            if (ClientType.valueOf(clientType) == ClientType.getClientType(clientSecret))
                return ClientType.valueOf(clientType);
        }
        return null;
    }
}
