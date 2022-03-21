package me.study.hateoas.accounts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @FileName AccountService.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 계정 Service
 **/
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder){
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @Description SpringSecurity, username로 계정 정보 가져오기
     * @Param username
     * @Retrun UserDetails
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =  accountRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        return new AccountAdapter(account);
    }

    /**
     * @Description 계정 저장
     * @Param account
     * @Retrun Account
     **/
    public Account saveAccount(Account account) {
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        return this.accountRepository.save(account);
    }
}
