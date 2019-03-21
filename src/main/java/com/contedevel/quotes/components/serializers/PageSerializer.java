package com.contedevel.quotes.components.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page<Object>> {

    @Override
    public void serialize(Page<Object> page, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("type", "page");
        gen.writeObjectField("content", page.getContent());
        gen.writeBooleanField("first", page.isFirst());
        gen.writeBooleanField("last", page.isLast());
        gen.writeBooleanField("prev", page.hasPrevious());
        gen.writeBooleanField("next", page.hasNext());
        gen.writeNumberField("pages", page.getTotalPages());
        gen.writeNumberField("elements", page.getTotalElements());
        gen.writeNumberField("length", page.getNumberOfElements());
        gen.writeNumberField("size", page.getSize());
        gen.writeNumberField("number", page.getNumber());
        gen.writeEndObject();
    }
}
