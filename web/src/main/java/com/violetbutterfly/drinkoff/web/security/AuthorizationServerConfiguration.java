package com.violetbutterfly.drinkoff.web.security;


import com.violetbutterfly.drinkoff.api.enums.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.inject.Inject;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String[] SCOPES = new String[]{"read", "write", "trust"};
    private static final String[] GRANT_TYPES = new String[]{"password", "authorization_code", "refresh_token", "implicit"};
    private static final String[] AUTHORITIES = new String[]{"ROLE_CLIENT", "ROLE_TRUSTED_CLIENT"};
    private static final int WEB_ACCESS_TOKEN_VALIDITY = 300; // seconds
    private static final int REST_ACCESS_TOKEN_VALIDITY = 600;
    private static final int REFRESH_TOKEN_VALIDITY = 1200;

    @Inject
    private TokenStore tokenStore;

    @Inject
    private UserApprovalHandler userApprovalHandler;

    @Inject
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(ClientType.COMPANY_WEB.name())
                .authorizedGrantTypes(GRANT_TYPES)
                .authorities(AUTHORITIES)
                .scopes(SCOPES)
                .secret(ClientType.COMPANY_WEB.getSecret())
                .accessTokenValiditySeconds(WEB_ACCESS_TOKEN_VALIDITY).
                refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY)
                .and().withClient(ClientType.PLAYER_MOBILE.name())
                .authorizedGrantTypes(GRANT_TYPES)
                .authorities(ClientType.COMPANY_WEB.name(), Role.COMPANY.name())
                .scopes(SCOPES)
                .secret(ClientType.PLAYER_MOBILE.getSecret())
                .accessTokenValiditySeconds(REST_ACCESS_TOKEN_VALIDITY)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }
}
