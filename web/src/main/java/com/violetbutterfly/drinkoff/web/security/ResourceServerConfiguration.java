package com.violetbutterfly.drinkoff.web.security;

import com.violetbutterfly.drinkoff.api.enums.Role;
import com.violetbutterfly.drinkoff.web.Uri;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import static com.violetbutterfly.drinkoff.web.Uri.*;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String[] create = new String[]{
            ROOT_URI_USER + Uri.Part.CREATE + Uri.Part.ALL,
            ROOT_URI_COMPANY + Uri.Part.CREATE + Uri.Part.ALL,
            ROOT_URI_DISCOUNT + Uri.Part.CREATE + Uri.Part.ALL
    };

    private static final String[] update = new String[]{
            ROOT_URI_USER + Uri.Part.UPDATE + Uri.Part.ALL,
            ROOT_URI_COMPANY + Uri.Part.UPDATE + Uri.Part.ALL,
            ROOT_URI_DISCOUNT + Uri.Part.UPDATE + Uri.Part.ALL
    };

    private static final String[] delete = new String[]{
            ROOT_URI_USER + Uri.Part.ONE_SEGMENT,
            ROOT_URI_COMPANY + Uri.Part.ONE_SEGMENT,
            ROOT_URI_DISCOUNT + Uri.Part.ONE_SEGMENT
    };

    private static final String[] findAll = new String[]{
            ROOT_URI_COMPANY,
            ROOT_URI_DISCOUNT,
            ROOT_URI_COMPANY+ Uri.Part.ALL,
            ROOT_URI_DISCOUNT + Uri.Part.ALL
    };

    private static final String[] findUsers = new String[]{
            ROOT_URI_USER,
            ROOT_URI_USER + Uri.Part.ALL
    };

    private static final String[] me = new String[]{
            ROOT_URI_USER + Uri.Part.ME
    };

    private static final String[] findById = new String[]{
            ROOT_URI_USER + Uri.Part.FIND + Uri.Part.ONE_SEGMENT,
            ROOT_URI_COMPANY + Uri.Part.FIND + Uri.Part.ONE_SEGMENT,
            ROOT_URI_DISCOUNT + Uri.Part.FIND + Uri.Part.ONE_SEGMENT
    };

    private static final String[] find = new String[]{
            ROOT_URI_USER + Uri.Part.FIND,
            ROOT_URI_COMPANY + Uri.Part.FIND,
            ROOT_URI_DISCOUNT + Uri.Part.FIND
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, create).authenticated()
                .antMatchers(HttpMethod.POST, update).authenticated()
                .antMatchers(HttpMethod.DELETE, delete).authenticated()
                .antMatchers(HttpMethod.GET, findAll).authenticated()
                .antMatchers(HttpMethod.GET, findById).authenticated()
                .antMatchers(HttpMethod.GET, find).authenticated()
                .antMatchers(HttpMethod.GET, me).authenticated()
                .antMatchers(HttpMethod.GET, findUsers).hasRole(Role.ADMIN.name())
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
