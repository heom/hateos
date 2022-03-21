package me.study.hateoas.events;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @FileName EventRepository.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 이벤트 Repository
 **/
public interface EventRepository  extends JpaRepository<Event, Integer> {

}
