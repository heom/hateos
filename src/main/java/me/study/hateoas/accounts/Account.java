package me.study.hateoas.accounts;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @FileName Account.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 계정 Entity
 **/
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;
}
