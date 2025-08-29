package apiTest.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonConverter {

    public static <T> T jsonToObject(File data, Class<T> obj) {
        try {
            return new ObjectMapper().readValue(data, obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObject(String data, Class<T> obj) {
        try {
            return new ObjectMapper().readValue(data, obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String objectToJson(Class<T> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
