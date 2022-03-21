package me.study.hateoas.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

/**
 * @FileName AppProperties.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description properties 커스텀 객체
 **/
@Component
@ConfigurationProperties(prefix = "my-app") //**중요**
@Getter @Setter
public class AppProperties {
    @NotEmpty
    private String adminUsername;
    @NotEmpty
    private String adminPassword;
    @NotEmpty
    private String userUsername;
    @NotEmpty
    private String userPassword;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String clientSecret;
}
