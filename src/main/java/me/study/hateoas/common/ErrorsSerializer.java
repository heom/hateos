package me.study.hateoas.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

/**
 * @FileName ErrorsSerializer.java
 * @Author ccs
 * @Version 1.0.0
 * @Date 2022-03-21
 * @Description 에러시 Json 파싱을 위한 커스텀 설정
 **/
@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeFieldName("errors");
        gen.writeStartArray();
        errors.getFieldErrors().forEach(x->{ //필드 에러
            try{
                gen.writeStartObject();
                gen.writeStringField("field",x.getField());
                gen.writeStringField("objectName",x.getObjectName());
                gen.writeStringField("code",x.getCode());
                gen.writeStringField("defaultMessage",x.getDefaultMessage());

                Object rejectValue = x.getRejectedValue();
                if(rejectValue != null){
                    gen.writeStringField("rejectValue",rejectValue.toString());
                }

                gen.writeEndObject();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        });
        errors.getGlobalErrors().forEach(x->{ //글로벌 에러
            try{
                gen.writeStartObject();
                gen.writeStringField("objectName",x.getObjectName());
                gen.writeStringField("code",x.getCode());
                gen.writeStringField("defaultMessage",x.getDefaultMessage());
                gen.writeEndObject();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        });
        gen.writeEndArray();
    }
}
