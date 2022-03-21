package me.study.hateoas.events;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import me.study.hateoas.accounts.Account;
import me.study.hateoas.accounts.AccountSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @FileName Event.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 이벤트 Entity
 **/
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of={"id"})
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional)이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;
    @ManyToOne
    @JsonSerialize(using = AccountSerializer.class)
    private Account manager;

    public void update() {
        //Update free
        this.free = this.basePrice == 0 && this.maxPrice == 0?true:false;

        //Update offline
        this.offline = this.location == null || this.location.trim().equals("")?false:true;
    }
}
