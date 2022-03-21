package me.study.hateoas.accounts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @FileName AccountAdapter.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Account / SpringSecurity(User) - Adapter
 **/
public class AccountAdapter extends User {
    private Account account;

    public AccountAdapter(Account account){
        super(account.getEmail(), account.getPassword(), authorities(account.getRoles()));
        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
        return roles.stream()
                .map(x->new SimpleGrantedAuthority("ROLE_"+x.name()))
                .collect(Collectors.toSet());
    }

    public Account getAccount(){
        return account;
    }
}
