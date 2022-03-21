package me.study.hateoas.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @FileName AccountRepository.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 계정 Repository
 **/
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String username);
}
