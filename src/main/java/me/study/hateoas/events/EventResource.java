package me.study.hateoas.events;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @FileName EventResource.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 이벤트 반환 시 link 포함
 **/
public class EventResource extends EntityModel<Event> {
    public EventResource(Event event, Link... links) {
        super(event, Arrays.asList(links));
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
}
