package me.study.hateoas.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

/**
 * @FileName EventValidator.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 이벤트 dto 데이터 검증
 **/
@Component
public class EventValidator {
    public void validate(EventDto eventDto, Errors erros){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0){
            erros.reject("wrongPrice", "Values of prices are wrong");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
        LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();

        if(endEventDateTime.isBefore(beginEventDateTime)
                || endEventDateTime.isBefore(closeEnrollmentDateTime)
                || endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            erros.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is wrong.");
        }

        if(beginEventDateTime.isBefore(closeEnrollmentDateTime)
            || beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            erros.rejectValue("beginEventDateTime", "wrongValue", "BeginEventDateTime is wrong.");
        }

        if(closeEnrollmentDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            erros.rejectValue("closeEnrollmentDateTime", "wrongValue", "CloseEnrollmentDateTime is wrong.");
        }
    }
}
