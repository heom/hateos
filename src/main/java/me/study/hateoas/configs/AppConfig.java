package me.study.hateoas.configs;


import me.study.hateoas.accounts.Account;
import me.study.hateoas.accounts.AccountRole;
import me.study.hateoas.accounts.AccountService;
import me.study.hateoas.common.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @FileName AppConfig.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Bean 등록 및 구동 시 Data insert
 **/
@Configuration
public class AppConfig {
    /**
     * @Description Object converter Bean 등록
     * @Retrun ModelMapper
     **/
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /**
     * @Description Spring Security 비밀번호 encoder Bean 등록
     * @Retrun PasswordEncoder
     **/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * @Description 구동 시 Account Data insert
     **/
    @Bean
    public ApplicationRunner applicationRunner(){
        return new ApplicationRunner() {

            @Autowired
            AccountService accountService;
            @Autowired
            AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args){
                Account adminAccount = Account.builder()
                        .email(appProperties.getAdminUsername())
                        .password(appProperties.getAdminPassword())
                        .roles(Stream.of(AccountRole.ADMIN).collect(Collectors.toSet()))
                        .build();
                accountService.saveAccount(adminAccount);

                Account userAccount = Account.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(Stream.of(AccountRole.USER).collect(Collectors.toSet()))
                        .build();
                accountService.saveAccount(userAccount);
            }
        };
    }
}
