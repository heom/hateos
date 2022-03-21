package me.study.hateoas.configs;


import me.study.hateoas.accounts.AccountService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @FileName SecurityConfig.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Bean 등록 및 Spring Security 커스텀 설정
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(AccountService accountService, PasswordEncoder passwordEncoder){
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @Description TokenStore(인메모리) Bean 등록
     * @Retrun TokenStore
     **/
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    /**
     * @Description AuthenticationManager Bean 등록
     * @Retrun AuthenticationManager
     **/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    /**
     * @Description accountService 서비스 등록 및 Spring Security 비밀번호 encoder 커스텀 설정
     * @Param auth
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * @Description RestDoc 및 RootUrl Spring Security 제외 커스텀 설정
     * @Param web
     **/
    @Override
    public void configure(WebSecurity web){
        web.ignoring().mvcMatchers("/docs/index.html");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
