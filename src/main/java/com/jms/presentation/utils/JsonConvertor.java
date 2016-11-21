package com.jms.presentation.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

/**
 * 
 * @author williamzhang
 *
 */
public class JsonConvertor {

    public static String toJSON(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        return objectMapper.writeValueAsString(obj);
    }
}
