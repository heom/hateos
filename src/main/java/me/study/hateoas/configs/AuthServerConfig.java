package me.study.hateoas.configs;

import me.study.hateoas.accounts.AccountService;
import me.study.hateoas.common.AppProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @FileName AuthServerConfig.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Spring Oauth2 서버 커스텀 설정
 **/
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final TokenStore tokenStore;
    private final AppProperties appProperties;

    public AuthServerConfig(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager
                            , AccountService accountService, TokenStore tokenStore
                            , AppProperties appProperties){
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.tokenStore = tokenStore;
        this.appProperties = appProperties;
    }

    /**
     * @Description Oauth2 Spring Security 비밀번호 encoder 커스텀 설정
     * @Param security
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security.passwordEncoder(passwordEncoder);
    }

    /**
     * @Description Oauth2 인증토큰 커스텀 설정
     * @Param clients
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(appProperties.getClientId())
                .authorizedGrantTypes("password","refresh_token")
                .scopes("read", "write")
                .secret(passwordEncoder.encode(appProperties.getClientSecret()))
                .accessTokenValiditySeconds(10 * 60)
                .refreshTokenValiditySeconds(6* 10 *60);
    }

    /**
     * @Description Oauth2 인증서비스 커스텀 설정
     * @Param endpoints
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(accountService)
                .tokenStore(tokenStore);
    }
}
