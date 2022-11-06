package org.library.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class BaseUnMarshalling {
    public static Map<String, String> parse(InputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> dataMap = mapper.readValue(
                inputStream, new TypeReference<Map<String, String>>() {
                });
        return dataMap;
    }
}
