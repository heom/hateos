package me.study.hateoas.index;

import me.study.hateoas.events.EventController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @FileName IndexController.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description Hateoas 위한 테스트용 Index 컨트롤러
 **/
@RestController
public class IndexController {

    /**
     * @Description 첫 페이지 및 에러 시 index 페이지 링크
     * @Retrun RepresentationModel
     **/
    @GetMapping("/api")
    public RepresentationModel index(){
        RepresentationModel index = new RepresentationModel();
        index.add(linkTo(EventController.class).withRel("events"));
        return index;
    }
}
