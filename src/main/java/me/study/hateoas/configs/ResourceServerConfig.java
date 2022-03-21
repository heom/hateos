package me.study.hateoas.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @FileName ResourceServerConfig.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Spring Oauth2 API 커스텀 설정
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * @Description resourceId 지정(차후 찾아봐야함)
     * @Param resources
     **/
    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId("event");
    }

    /**
     * @Description Oauth2 API 서버 권한 설정
     * @Param http
     **/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous()
                    .and()
                .authorizeRequests()
                    .mvcMatchers(HttpMethod.GET, "/api/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
