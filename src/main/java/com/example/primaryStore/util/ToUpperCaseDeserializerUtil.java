package com.example.primaryStore.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ToUpperCaseDeserializerUtil extends StdDeserializer<String> {

    public ToUpperCaseDeserializerUtil() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return _parseString(p, ctxt).toUpperCase();
    }

}
